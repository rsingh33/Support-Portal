package com.citi.spring.web.dao;


import com.citi.spring.web.dao.entity.ExcelRow;
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
@Component("excelDao")
public class ExcelDao {
    private static Logger logger = Logger.getLogger(ExcelDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }


    public List<ExcelRow> getExcelRow() {

        Query query = session().createQuery("from ExcelRow");
        return query.list();
    }


    public ExcelRow getExcelRow(int id) {
        Query query = session().createQuery("from ExcelRow where id = :id");
        query.setParameter("id", id);

        return (ExcelRow) query.uniqueResult();
    }

    public boolean deleteExcelRow(int id) {
        Query query = session().createQuery("delete from ExcelRow where id =:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public boolean deleteEverything(Session session) {
        Query query = session.createQuery("delete from ExcelRow");
        return query.executeUpdate() == 1;
    }

    public void saveOrUpdateExcelRow(ExcelRow excelRow) {
        session().saveOrUpdate(excelRow);
    }

    public void saveorUpdateAll(List<ExcelRow> list) {
        System.out.println("Entering save and update");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {


            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                System.out.println("checking if ");
                tx = session.getTransaction();
                System.out.println(tx.getLocalStatus());
                System.out.println("got transaction ");
            } else {
                System.out.println("checking else");
                tx = session.beginTransaction();
                System.out.println("Was committed " + tx.wasCommitted() + " " + tx.wasRolledBack());
            }

            int count = 0;
            deleteEverything(session);
            for (Iterator itrList = list.listIterator(); itrList.hasNext(); ) {
                ExcelRow record = (ExcelRow) itrList.next();
                session.saveOrUpdate(record);
                if (count % 20 == 0) {
                    session().flush();
                    session().clear();
                }
            }
            System.out.println(" about to commit Transaction");
            if (!tx.wasCommitted()) {
                tx.commit();
            }
            System.out.println("Transaction is commited");


        } catch (Exception e) {
            System.out.println("Session Flush mode is  " + session().getFlushMode());
            System.out.println(e.getCause());
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            System.out.println("closing session");
            session.close();
            System.out.println("session closed");
        }

    }

}
