package com.citi.spring.web.dao.rowMappers;

import com.citi.spring.web.dao.entity.Issue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueRowMapper implements RowMapper<Issue> {

    @Override
    public Issue mapRow(ResultSet resultSet, int i) throws SQLException {


        Issue issue = new Issue();

        issue.setId(resultSet.getInt("id"));
        issue.setSolution(resultSet.getString("solution"));
        issue.setWorkaround(resultSet.getString("workaround"));
        issue.setJira(resultSet.getString("jira"));
        issue.setSourceSystem(resultSet.getString("sourceSystem"));
        issue.setLastMod(resultSet.getTimestamp("lastMod"));
        issue.setIssueDescription(resultSet.getString("issueDescription"));


        return issue;
    }
}
