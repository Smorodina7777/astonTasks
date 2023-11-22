package org.example.daoImpl;


import org.example.dao.CarDao;
import org.example.entity.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public List<Car> getAllCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car c join fetch c.owner");
        return query.getResultList();
    }
}
