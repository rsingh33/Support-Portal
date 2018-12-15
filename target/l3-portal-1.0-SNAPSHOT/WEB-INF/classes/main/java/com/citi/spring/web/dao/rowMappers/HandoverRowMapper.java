package com.citi.spring.web.dao.rowMappers;

import com.citi.spring.web.dao.entity.Handover;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HandoverRowMapper implements RowMapper<Handover> {

    @Override
    public Handover mapRow(ResultSet resultSet, int i) throws SQLException {


        Handover handover = new Handover();

        handover.setId(resultSet.getInt("id"));
        handover.setStatus(resultSet.getString("status"));
        handover.setCurrentlyWith(resultSet.getString("currentlyWith"));
        handover.setComments(resultSet.getString("comments"));
        handover.setEmailSubject(resultSet.getString("email"));
        handover.setEnvironment(resultSet.getString("environment"));
        handover.setReportedBy(resultSet.getString("reportedBy"));
        handover.setTracking(resultSet.getString("tracking"));
        handover.setLastMod(resultSet.getTimestamp("lastMod"));

        return handover;
    }
}
