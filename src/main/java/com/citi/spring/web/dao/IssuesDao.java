package com.citi.spring.web.dao;

import com.citi.spring.web.dao.entity.Issue;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Component("issueDao")
public class IssuesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Issue> getIssue() {
        Query query = session().createQuery("from Issue");
        return query.list();
    }


    public Issue getIssue(int id) {
        Query query = session().createQuery("from Issue where id = :id");
        query.setParameter("id", id);
        return (Issue) query.uniqueResult();
    }


    public List<Issue> getIssue(String keyword) {
        Criteria query = session().createCriteria(Issue.class);
        query.add(Restrictions.like("issueDescription", keyword, MatchMode.ANYWHERE));
        return query.list();
    }



    public boolean deleteIssue(int id) {
        Query query = session().createQuery("delete from Issue where id =:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public void saveOrUpdateIssue(Issue issue) {
        session().saveOrUpdate(issue);
    }

}
