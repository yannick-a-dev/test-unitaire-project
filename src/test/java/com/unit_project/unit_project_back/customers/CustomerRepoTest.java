package com.unit_project.unit_project_back.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerRepoTest {
 @Autowired
 CustomerRepo customerRepo;

 @Test
 public void shouldReturnAllCustomers(){
     //Arrange
     Customer customer1 = Customer.builder().id(1).email("essola@yahoo.fr").build();
     Customer customer2 = Customer.builder().id(2).email("nouma@yahoo.fr").build();
     this.customerRepo.saveAll(List.of(customer1,customer2));
     
     //Act
     List<Customer> customerList = this.customerRepo.findAll();

     //Assert
     Assertions.assertEquals(2, customerList.size());
 }

    @Test
    public void shouldReturnAllCustomerByEmail(){
        //Arrange
        Customer customer1 = Customer.builder().id(1).email("essola@yahoo.fr").build();
        Customer customer2 = Customer.builder().id(2).email("nouma@yahoo.fr").build();
        this.customerRepo.saveAll(List.of(customer1,customer2));

        //Act
        Customer customer = this.customerRepo.findByEmail("essola@yahoo.fr");

        //Assert
        Assertions.assertEquals(customer1.getId(), customer.getId());
        Assertions.assertEquals(customer1.getEmail(), customer.getEmail());
    }
}