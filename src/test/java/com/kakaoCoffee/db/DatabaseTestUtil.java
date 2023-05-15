package com.kakaoCoffee.db;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Component
public class DatabaseTestUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM point_histories");
        jdbcTemplate.execute("DELETE FROM orders");
        jdbcTemplate.execute("DELETE FROM members");
        jdbcTemplate.execute("DELETE FROM beverages");
    }

}
