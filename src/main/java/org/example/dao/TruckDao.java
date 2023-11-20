package org.example.dao;


import org.example.config.Config;
import org.example.entity.Truck;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class TruckDao {

    @Transactional
    public void saveTruck(String mark, String model, int year, String tonnage) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Truck truck = new Truck();
            truck.setMark(mark);
            truck.setModel(model);
            truck.setYear(year);
            truck.setTonnage(tonnage);
            session.save(truck);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Transactional
    public void removeTruckById(int id) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Truck where id = :param");
            query.setParameter("param", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Transactional
    public Truck getTruckById(int id) {
        Transaction transaction = null;
        Truck truck = new Truck();
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Truck> query = session.createQuery("from Truck where id = :param");
            query.setParameter("param", id);
            truck = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return truck;
    }

    @Transactional
    public List<Truck> getAllTrucks() {
        List<Truck> Trucks = Collections.emptyList();
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Truck> query = session.createQuery("from Truck t join fetch t.owner");
            Trucks = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return Trucks;
    }

    @Transactional
    public void cleanTruckTable() {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE Truck");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }
}
