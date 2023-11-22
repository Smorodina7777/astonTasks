package org.example.daoImpl;


import org.example.dao.PassCarDao;
import org.example.entity.Owner;
import org.example.entity.Part;
import org.example.entity.PassengerCar;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class PassCarDaoImpl implements PassCarDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void savePassengerCar(String mark, String model, int year, Owner owner, Set<Part> parts, String bodyType) {
            sessionFactory.getCurrentSession().save(new PassengerCar(mark, model, year, owner, parts, bodyType));
    }

    @Transactional
    public void removePassengerCarById(int id) {
            Query query = sessionFactory.getCurrentSession().createQuery("delete PassengerCar where id = :param");
            query.setParameter("param", id);
            query.executeUpdate();
    }

    @Transactional
    public PassengerCar getPassById(int id) {
            TypedQuery<PassengerCar> query = sessionFactory.getCurrentSession().createQuery("from PassengerCar where id = :param");
            query.setParameter("param", id);
            return query.getSingleResult();
    }

    @Transactional
    public List<PassengerCar> getAllPassengerCars() {
            TypedQuery<PassengerCar> query = sessionFactory.getCurrentSession().createQuery("from PassengerCar p join fetch p.owner");
             return query.getResultList();
    }

    @Transactional
    public void cleanPassengerCarTable() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE PassengerCar");
        query.executeUpdate();
    }

}
