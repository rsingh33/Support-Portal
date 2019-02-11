package com.citi.spring.web.dao;


import com.citi.spring.web.dao.entity.Handover;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Component
public class HandoverDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }


    public List<Handover> getHandover() {

        Query query = session().createQuery("from Handover");
        return query.list();
    }

    public List<Handover> getHandover(String reportedBy) {

        Query query = session().createQuery("from Handover where reportedBy = :reportedBy");
        query.setParameter("reportedBy", reportedBy);
        return query.list();
    }


    public Handover getHandover(int id) {
        Query query = session().createQuery("from Handover where id = :id");
        query.setParameter("id", id);

        return (Handover) query.uniqueResult();
    }

    public boolean deleteHandover(int id) {
        Query query = session().createQuery("delete from Handover where id =:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public void saveOrUpdateHandover(Handover handover) {
        session().saveOrUpdate(handover);
    }

}
