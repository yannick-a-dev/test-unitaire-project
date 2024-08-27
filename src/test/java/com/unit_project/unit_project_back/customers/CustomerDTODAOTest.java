package com.unit_project.unit_project_back.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@JdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerDTODAOTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    CustomerDAO customerDAO;

    @Test
    void shouldReturnListOfCustomers() {
        //Arrange / Given
        customerDAO = new CustomerDAO(jdbcTemplate);
        //Act / When
        List<CustomerDTO> customerDTOS = this.customerDAO.search();

        //Assert / Then
        Assertions.assertEquals(7, customerDTOS.size());
    }
}