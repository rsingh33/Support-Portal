package com.citi.spring.web.dao;



import com.citi.spring.web.dao.entity.Backlog;
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
public class BacklogDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }


    public List<Backlog> getBacklog() {

        Query query = session().createQuery("from Backlog");
        return query.list();
    }

    public List<Backlog> getBacklog(String reportedBy) {

        Query query = session().createQuery("from Backlog where reportedBy = :reportedBy");
        query.setParameter("reportedBy", reportedBy);
        return query.list();
    }


    public Backlog getBacklog(int id) {
        Query query = session().createQuery("from Backlog where id = :id");
        query.setParameter("id", id);

        return (Backlog) query.uniqueResult();
    }

    public boolean deleteBacklog(int id) {
        Query query = session().createQuery("delete from Backlog where id =:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public void saveOrUpdateBacklog(Backlog backlog) {
        session().saveOrUpdate(backlog);
    }

}
