package com.citi.spring.web.dao;


import com.citi.spring.web.dao.entity.Monitor;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
@Component
public class MonitorDao {
    private static Logger logger = Logger.getLogger(MonitorDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }


    public List<Monitor> getMonitorUrls() {

        Query query = session().createQuery("from Monitor");
        return query.list();
    }


    public Monitor getMonitor(int id) {
        Query query = session().createQuery("from Monitor where id = :id");
        query.setParameter("id", id);

        return (Monitor) query.uniqueResult();
    }

    public boolean deleteMonitor(int id) {
        Query query = session().createQuery("delete from Monitor where id =:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public boolean deleteEverything(Session session) {
        Query query = session.createQuery("delete from Monitor");
        return query.executeUpdate() == 1;
    }

    public void saveOrUpdateMonitor(Monitor monitor) {
        session().saveOrUpdate(monitor);
    }

    public void saveorUpdateAll(List<Monitor> list) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {


            if (session.getTransaction() != null && session.getTransaction().isActive()) {

                tx = session.getTransaction();

            } else {

                tx = session.beginTransaction();

            }

            int count = 0;

            for (Iterator itrList = list.listIterator(); itrList.hasNext(); ) {
                Monitor record = (Monitor) itrList.next();
                session.saveOrUpdate(record);
                if (count % 20 == 0) {
                    session().flush();
                    session().clear();
                }
            }

            if (!tx.wasCommitted()) {
                tx.commit();
            }


        } catch (Exception e) {

            e.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }

        } finally {

            session.close();

        }

    }

}
