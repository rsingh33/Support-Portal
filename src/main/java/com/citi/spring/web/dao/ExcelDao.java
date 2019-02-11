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
@Component
public class ExcelDao {
    private static Logger logger = Logger.getLogger(ExcelDao.class);


    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }


    public List<ExcelRow> getExcelRow(String releaseName) {

        Query query = session().createQuery("from ExcelRow where releaseName = :releaseName");
        query.setParameter("releaseName", releaseName);
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

    public String saveorUpdateAll(List<ExcelRow> list) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String message;
        try {


            if (session.getTransaction() != null && session.getTransaction().isActive()) {

                tx = session.getTransaction();

            } else {
                tx = session.beginTransaction();

            }

            int count = 0;

            for (Iterator itrList = list.listIterator(); itrList.hasNext(); ) {
                ExcelRow record = (ExcelRow) itrList.next();
                logger.info("saving excelRow in database");
                logger.info(record.toString());
                session.saveOrUpdate(record);
                if (count % 20 == 0) {
                    session().flush();
                    session().clear();
                }
            }

            if (!tx.wasCommitted()) {
                tx.commit();
            }

            message = "File successfully processed and saved, please use dropdown to see release contents";
        } catch (Exception e) {

            logger.error("Exception occurred while saving enteries to release database " + e.getCause() + "  class name is " + e.getClass(), e);
            if (tx != null) {
                tx.rollback();
            }
            message = "Exception occurred while saving enteries to release table " + e.getCause() + "  class name is " + e.getClass();

        } finally {

            session.close();

        }
        return message;
    }

    public List<String> getReleases() {

        Query query = session().createQuery("Select distinct releaseName from ExcelRow");
        return query.list();

    }

    public boolean deleteExcel(String releaseName) {

        Query query = session().createQuery("delete from ExcelRow where releaseName = :releaseName");
        query.setParameter("releaseName", releaseName);
        return query.executeUpdate() == 1;
    }
}
