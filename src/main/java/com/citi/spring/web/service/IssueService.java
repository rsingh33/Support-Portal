package com.citi.spring.web.service;


import com.citi.spring.web.dao.IssuesDao;
import com.citi.spring.web.dao.entity.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssuesDao issuesDao;


    public List<Issue> getCurrentIssues() {
        return issuesDao.getIssue();
    }


    public List<Issue> getIssue(String keyword) {
        if (keyword == null) return null;
        List<Issue> issues = issuesDao.getIssue(keyword);

        if (issues.size() == 0) return null;

        return issues;
    }
    public Issue getIssue(int id) {
        if (id == 0) return null;
        Issue isssue = issuesDao.getIssue(id);

        return isssue;
    }
    public void saveOrUpdate(Issue issue) {

        issuesDao.saveOrUpdateIssue(issue);

    }

    public void delete(int id) {
        issuesDao.deleteIssue(id);
    }

}
