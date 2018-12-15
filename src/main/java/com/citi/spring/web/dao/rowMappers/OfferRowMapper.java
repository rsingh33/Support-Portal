package com.citi.spring.web.dao.rowMappers;


import com.citi.spring.web.dao.entity.Offer;
import com.citi.spring.web.dao.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {

    @Override
    public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setAuthority(resultSet.getString("authority"));
        user.setEnabled(true);
        user.setEmail(resultSet.getString("email"));
        user.setName(resultSet.getString("name"));
        user.setusername(resultSet.getString("username"));

        Offer offer = new Offer();
        offer.setId(resultSet.getInt("id"));
        offer.setText(resultSet.getString("text"));
        offer.setUser(user);
        return offer;
    }
}
