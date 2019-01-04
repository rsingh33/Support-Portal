package com.citi.spring.web.service;


import com.citi.spring.web.dao.HandoverDao;
import com.citi.spring.web.dao.entity.Handover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("handoverService")
public class HandoverService {
    private HandoverDao handoverDao;

    @Autowired
    public void setHandoverDao(HandoverDao handoverDao) {
        this.handoverDao = handoverDao;
    }

    public List<Handover> getCurrentHandover() {
        return handoverDao.getHandover();
    }


    public boolean hasHandoverEntry(String reportedBy) {

        if (reportedBy == null) {
            return false;
        }
        List<Handover> handovers = handoverDao.getHandover(reportedBy);

        if (handovers.size() == 0) {
            return false;
        }
        return true;
    }

    public List<Handover> getHandover(String reportedBy) {
        if (reportedBy == null) return null;
        List<Handover> handovers = handoverDao.getHandover(reportedBy);

        if (handovers.size() == 0) return null;

        return handovers;
    }

    public Handover getHandover(int id) {
        if (id == 0) return null;
        Handover handover = handoverDao.getHandover(id);

        return handover;
    }

    public void saveOrUpdate(Handover handover) {

        handoverDao.saveOrUpdateHandover(handover);

    }

    public void delete(int id) {
        handoverDao.deleteHandover(id);
    }


}
