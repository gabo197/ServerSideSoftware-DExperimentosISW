//US01 Como cliente quiero visualizar la lista de los trabajadores en la plataforma para conocer información relevante de ellos.
package com.appdhome.controller;

import com.appdhome.entities.Account;
import com.appdhome.entities.Customer;
import com.appdhome.entities.District;
import com.appdhome.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("tests")
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerServiceImpl customerService;

    private List<Customer> customerList;
    Account account = new Account();
    District district = new District();

    @BeforeEach
    void setUp(){
        customerList = new ArrayList<>();
        customerList.add(new Customer(1L, "Milagros", "Sotomayor", "78478541",
                "mili@hotmail.com", "998745247", account, district));
    }

    @Test
    void Watch() throws Exception{
        given(customerService.getAll()).willReturn(customerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }
}
