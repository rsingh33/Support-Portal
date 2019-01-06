package com.citi.spring.web.service;


import com.citi.spring.web.dao.BacklogDao;
import com.citi.spring.web.dao.entity.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("backlogService")
public class BacklogService {
    private BacklogDao backlogDao;

    @Autowired
    public void setBacklogDao(@Qualifier("backlogDao") BacklogDao backlogDao) {
        this.backlogDao = backlogDao;
    }

    public List<Backlog> getCurrentBacklog() {
        return backlogDao.getBacklog();
    }


    public boolean hasBacklogEntry(String reportedBy) {

        if (reportedBy == null) {
            return false;
        }
        List<Backlog> backlogs = backlogDao.getBacklog(reportedBy);

        if (backlogs.size() == 0) {
            return false;
        }
        return true;
    }

    public List<Backlog> getBacklog(String reportedBy) {
        if (reportedBy == null) return null;
        List<Backlog> backlogs = backlogDao.getBacklog(reportedBy);

        if (backlogs.size() == 0) return null;

        return backlogs;
    }

    public Backlog getBacklog(int id) {
        if (id == 0) return null;
        Backlog backlog = backlogDao.getBacklog(id);

        return backlog;
    }

    public void saveOrUpdate(Backlog backlog) {

        backlogDao.saveOrUpdateBacklog(backlog);

    }

    public void delete(int id) {
        backlogDao.deleteBacklog(id);
    }


}
