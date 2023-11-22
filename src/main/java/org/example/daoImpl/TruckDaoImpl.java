package org.example.daoImpl;


import org.example.dao.TruckDao;
import org.example.entity.Owner;
import org.example.entity.Part;
import org.example.entity.Truck;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class TruckDaoImpl implements TruckDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void saveTruck(String mark, String model, int year, Owner owner, Set<Part> parts, String tonnage) {
        sessionFactory.getCurrentSession().save(new Truck(mark, model, year, owner, parts, tonnage));
    }

    @Transactional
    public void removeTruckById(int id) {
            Query query = sessionFactory.getCurrentSession().createQuery("delete Truck where id = :param");
            query.setParameter("param", id);
            query.executeUpdate();       
    }

    @Transactional
    public Truck getTruckById(int id) {
            TypedQuery<Truck> query = sessionFactory.getCurrentSession().createQuery("from Truck where id = :param");
            query.setParameter("param", id);
            return query.getSingleResult();
    }

    @Transactional
    public List<Truck> getAllTrucks() {
        TypedQuery<Truck> query = sessionFactory.getCurrentSession().createQuery("from Truck p join fetch p.owner");
        return query.getResultList();
    }

    @Transactional
    public void cleanTruckTable() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE Truck");
        query.executeUpdate();
    }
        
}
