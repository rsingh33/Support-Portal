package com.citi.spring.web.service;


import com.citi.spring.web.dao.User;
import com.citi.spring.web.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usersService")
public class UsersService {

    private UsersDao usersDao;

    @Autowired
    public void setOffersDAO(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    public void create(User user) {
        usersDao.create(user);
    }


    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    @Secured("ROLE_admin")
    public List<User> getAllUsers() {

        return usersDao.getAllUsers();
    }
}
