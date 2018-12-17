package com.citi.spring.web.dao;


import com.citi.spring.web.dao.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionFactory sessionFactory;


    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session().save(user);


    }

    public boolean exists(String username) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.idEq(username));
        User user = (User) criteria.uniqueResult();

        return user != null;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return session().createQuery("from User").list();
    }

    public User findUserByEmail(String userEmail) {
        User user = null;
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", userEmail));
        return user;
    }

    public User findUserByResetToken(String resetToken) {
        User user = null;
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("resetToken", resetToken));
        return user;
    }

    public void update(User user) {
      session().saveOrUpdate(user);
    }

    public boolean existsToken(String token) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("resetToken",token));
        User user = (User) criteria.uniqueResult();

        return user != null;
    }
}


