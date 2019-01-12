package com.citi.spring.web.service;


import com.citi.spring.web.dao.MonitorDao;
import com.citi.spring.web.dao.data.MonitorHelper;
import com.citi.spring.web.dao.entity.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("monitorService")
public class MonitorService {

    @Autowired
    MonitorHelper monitorHelper;

    @Qualifier("monitorDao")
    @Autowired
    MonitorDao monitorDao;

    public void saveOrUpdate(Monitor monitor) {
        monitorDao.saveOrUpdateMonitor(monitor);
    }

    public List<Monitor> refresh() {
        List<Monitor> list = monitorDao.getMonitorUrls();
        monitorHelper.refreshAll(list);
        monitorDao.saveorUpdateAll(list);

        return list;
    }

    public List<Monitor> getMonitorEntities() {
        return monitorDao.getMonitorUrls();
    }

    public void delete(int id) {
        monitorDao.deleteMonitor(id);
    }

    public Monitor getMonitor(int id) {
        return monitorDao.getMonitor(id);
    }
}
