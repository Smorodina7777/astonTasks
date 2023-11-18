package org.example.dao;


import org.example.config.Config;
import org.example.entity.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class OwnerDao {


    public void saveOwner(String name) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Owner Owner = new Owner();
            Owner.setName(name);
//            Owner.setCars(cars);
            session.save(Owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    public void removeOwnerById(int id) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Owner where id = :param");
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

    public List<Owner> getAllOwners() {
        List<Owner> Owners = Collections.emptyList();
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Owner> query = session.createQuery("from Owner");
            Owners = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return Owners;
    }

    public void cleanOwnerTable() {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE Owner");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }
}
