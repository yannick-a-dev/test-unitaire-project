package com.unit_project.unit_project_back.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepo customerRepo;
    @InjectMocks
    CustomerService customerService;

    @DisplayName("[Service] Test liste des utilisateurs")
    @Test
    void shouldReturnAllCustomers() {
        //Arrange
        Customer customer1 = Customer.builder().id(1).email("essola@yahoo.fr").build();
        Customer customer2 = Customer.builder().id(2).email("nouma@yahoo.fr").build();
        when(this.customerRepo.findAll()).thenReturn(List.of(customer1,customer2));
        //Act
        List<CustomerDTO> customerList = this.customerService.search();
        //Assert
        Assertions.assertEquals(2, customerList.size());
    }

    @DisplayName("[Service] Test liste des utilisateurs")
    @Test
    void shouldReturnCustomerById() {
        //Arrange
        Customer customer1 = Customer.builder().id(1).email("essola@yahoo.fr").build();
        when(this.customerRepo.findById(1)).thenReturn(Optional.of(customer1));
        //Act
        CustomerDTO customerDTO = this.customerService.read(1);
        //Assert
        Assertions.assertEquals(customer1.getId(), customerDTO.id());
    }

    @Test
    void shouldThrowException(){
        when(this.customerRepo.findById(anyInt())).thenReturn(Optional.empty());

        //Act
        //Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> this.customerService.read(1));

        assertEquals("No customer for id 1",exception.getMessage());
    }
}