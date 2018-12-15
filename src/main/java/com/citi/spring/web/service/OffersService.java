package com.citi.spring.web.service;

import com.citi.spring.web.dao.OffersDao;
import com.citi.spring.web.dao.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("offersService")
public class OffersService {

    private OffersDao offersDAO;

    @Autowired
    public void setOffersDAO(OffersDao offersDAO) {
        this.offersDAO = offersDAO;
    }

    public List<Offer> getCurrent() {
        return offersDAO.getOffers();
    }

    @Secured({"ROLE_user", "ROLE_admin"})
    public void create(Offer offer) {
        offersDAO.saveOrUpdate(offer);
    }


    public boolean hasOffer(String name) {

        if (name == null) {
            return false;
        }
        List<Offer> offers = offersDAO.getOffers(name);

        if (offers.size() == 0) {
            return false;
        }
        return true;
    }

    public Offer getOffer(String username) {
        if (username == null) return null;
        List<Offer> offers = offersDAO.getOffers(username);

        if (offers.size() == 0) return null;

        return offers.get(0);
    }

    public void saveOrUpdate(Offer offer) {

        offersDAO.saveOrUpdate(offer);

    }

    public void delete(int id) {
        offersDAO.delete(id);
    }
}
