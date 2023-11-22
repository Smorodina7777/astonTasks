package org.example.daoImpl;


import org.example.dao.PartDao;
import org.example.entity.Car;
import org.example.entity.Part;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class PartDaoImpl implements PartDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void savePart(String title, Set<Car> carSet) {
        sessionFactory.getCurrentSession().save(new Part(title, carSet));
    }
    @Transactional
    public void savePart(String title) {
        sessionFactory.getCurrentSession().save(new Part(title));
    }

    @Transactional
    public void removePartById(int id) {
            Query query = sessionFactory.getCurrentSession().createQuery("delete Part where id = :param");
            query.setParameter("param", id);
            query.executeUpdate();       
    }

    @Transactional
    public Part getPartById(int id) {
            TypedQuery<Part> query = sessionFactory.getCurrentSession().createQuery("from Part where id = :param");
            query.setParameter("param", id);
            return query.getSingleResult();
    }

    @Transactional
    public List<Part> getAllParts() {
        TypedQuery<Part> query = sessionFactory.getCurrentSession().createQuery("from Part p  left join fetch p.carSet");
        return query.getResultList();
    }

    @Transactional
    public void cleanPartTable() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE Part");
        query.executeUpdate();
    }
        
}
