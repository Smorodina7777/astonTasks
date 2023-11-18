package org.example.dao;


import org.example.config.Config;
import org.example.entity.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class CarDao {

    @Transactional
    public void removeCarById(int id) {
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Car where id = :param");
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
    public List<Car> getAllCars() {
        List<Car> Cars = Collections.emptyList();
        int n = 1;
        Transaction transaction = null;
        try (Session session = Config.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car c join fetch c.owner");
            System.out.println(n++);
            Cars = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return Cars;
    }
}
