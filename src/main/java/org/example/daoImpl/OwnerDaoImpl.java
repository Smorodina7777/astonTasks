package org.example.daoImpl;


import org.example.dao.OwnerDao;
import org.example.entity.Car;
import org.example.entity.Owner;
import org.example.entity.Truck;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class OwnerDaoImpl implements OwnerDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void saveOwner(String name, Set<Car> cars) {
        sessionFactory.getCurrentSession().save(new Owner(name, cars));
    }

    @Transactional
    public Owner getOwnerById(int id) {
        TypedQuery<Owner> query = sessionFactory.getCurrentSession().createQuery("from Owner where id = :param");
        query.setParameter("param", id);
        return query.getSingleResult();
    }

    @Transactional
    public Owner updateOwnerById(int id, Set<Car> cars) {
        TypedQuery<Owner> query = sessionFactory.getCurrentSession().createQuery("update Owner set cars = : cars where id = :param");
        query.setParameter("param", id);
        query.setParameter("cars", cars);
        return query.getSingleResult();
    }

    @Transactional
    public List<Owner> getAllOwners() {
        TypedQuery<Owner> query = sessionFactory.getCurrentSession().createQuery("from Owner p join fetch p.cars");
        return query.getResultList();
    }


}
