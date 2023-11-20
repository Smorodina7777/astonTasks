package org.example.dao;


import org.example.config.Config;
import org.example.entity.Car;
import org.example.entity.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class PartDao {

    @Transactional
    public void savePart(String title) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Part Part = new Part();
            Part.setTitle(title);
            session.save(Part);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Transactional
    public void updatePartById(Set<Car> carSet, int id) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Part set carSet = :newCarSet where id = :param");
            query.setParameter("param", id);
            query.setParameter("newCarSet", carSet);
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
    public void removePartById(int id) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Part where id = :param");
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
    public Part getPartById(int id) {
        Transaction transaction = null;
        Part part = new Part();
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Part> query = session.createQuery("from Part  where id = :param");
            query.setParameter("param", id);
            part = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return part;
    }


    @Transactional
    public List<Part> getAllParts() {
        List<Part> Parts = Collections.emptyList();
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Part> query = session.createQuery("from Part p left join fetch p.carSet");
            Parts = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return Parts;
    }

    @Transactional
    public void cleanPartTable() {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE Part");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }


}
