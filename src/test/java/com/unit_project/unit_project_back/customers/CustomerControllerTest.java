package com.unit_project.unit_project_back.customers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomerService customerService;

    @DisplayName("[Crontroller] Test liste des utilisateurs")
    @Test
    void shouldReturnListOfCustomers() throws Exception {
        //Arrange
        CustomerDTO customer1 =new CustomerDTO(1,"essola@yahoo.fr");
        CustomerDTO customer2 =new CustomerDTO(2,"nouma@yahoo.fr");

        when(this.customerService.search()).thenReturn(List.of(customer1,customer2));

        //Act & Assert
        this.mockMvc
                .perform(get("/customers"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("essola@yahoo")));
    }

    @DisplayName("[Crontroller] Test lecture d'un utilisateur")
    @Test
    void shouldReturnOneCustomer() throws Exception {
        CustomerDTO customer2 =new CustomerDTO(2,"nouma@yahoo.fr");
        when(this.customerService.read(anyInt())).thenReturn(customer2);

        this.mockMvc
                .perform(get("/customers/2"))
                .andExpect(jsonPath("$.email").value(customer2.email()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}