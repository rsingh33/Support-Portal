package com.citi.spring.web.service;

import com.citi.spring.web.dao.UsersDao;
import com.citi.spring.web.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService {


    @Autowired
    private UsersDao usersDao;





    public void create(User user) {
        usersDao.create(user);
    }


    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    public boolean existsByEmail(String email) {
        return usersDao.existsByEmail(email);
    }

    public boolean existsToken(String token) {
        return usersDao.existsToken(token);
    }


    @Secured("ROLE_admin")
    public List<User> getAllUsers() {

        return usersDao.getAllUsers();
    }

    public User findUserByEmail(String userEmail) {
        return usersDao.findUserByEmail(userEmail);
    }

    public User findUserByUsername(String username) {
        return usersDao.findUserByUsername(username);
    }

    public User findUserByResetToken(String resetToken) {
        return usersDao.findUserByResetToken(resetToken);
    }

    public void update(User user) {
        usersDao.update(user);
    }

    public void delete(String username) {
        usersDao.delete(username);
    }

    public User getUser(String username) {
        return usersDao.getUser(username);
    }

    public User getUserEmail(String email) {
        return usersDao.getUserEmail(email);
    }


    public User getUser(int id) {
        return usersDao.getUser(id);
    }

    public void saveOrUpdate(User user) {
        usersDao.saveOrUpdate(user);
    }
}
