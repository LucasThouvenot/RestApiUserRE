package com.RessourcesRekationnel.Rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class DatabaseTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testQuery() {
        int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM commentaire", Integer.class);
        assertEquals(4, rowCount); // Adjust as needed based on your mock data
    }
}
