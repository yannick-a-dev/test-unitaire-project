package com.unit_project.unit_project_back.customers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CustomerService {

    CustomerRepo customerRepo;
    public List<CustomerDTO> search(){
     return this.customerRepo.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getEmail())).collect(Collectors.toList());
    }

    public CustomerDTO read(int id){
       Customer customer = this.customerRepo.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No customer for id " + id));
       return new CustomerDTO(customer.getId(), customer.getEmail());
    }
}
