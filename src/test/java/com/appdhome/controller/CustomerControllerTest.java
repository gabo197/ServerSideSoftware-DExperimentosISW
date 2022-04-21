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
import java.util.Optional;

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
        customerList.add(new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com", "998745247", account, district));
        customerList.add(new Customer(2L, "Julissa", "Ponte", "74470551", "juli@hotmail.com", "987654321", new Account(2L, "juli", "654321", 1, true ), district));
        customerList.add(new Customer(1L, "Katerin", "Villalobos", "78858545","katy@hotmail.com", "97374520", account, district));
    }
    
    @Test
    void CustomerList() throws Exception{
        Customer customer = customerList.get(1);
        Long id = customer.getId();

        Optional<Customer> expected = customerService.getById(id);
        if (expected.isPresent()) {
            mockMvc.perform(get("/api/customers/3")).andExpect(status().isOk());
        }
    }

    @Test
    void findAll() throws Exception{
        given(customerService.getAll()).willReturn(customerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }

//    US16: Como trabajador quiero revisar el perfil de un cliente antiguo para tener su información de contacto.
    @Test
    void findById() throws Exception{
        Customer customer = customerList.get(1);
        Long id = customer.getId();

        Optional<Customer> expected = customerService.getById(id);
        if (expected.isPresent()) {
            mockMvc.perform(get("/api/customers/1")).andExpect(status().isOk());
        }
    }

    @Test
    void findByAccountId() throws Exception{
        Customer customer = customerList.get(1);
        Long accountId = customer.getAccount().getId();

        Optional<Customer> expected = customerService.findByIdAccount(accountId);
        if (expected.isPresent()) {
            mockMvc.perform(get("/api/customers/searchDni/2")).andExpect(status().isOk());
        }
    }

    @Test
    void findByDni() throws Exception{
        Customer customer = customerList.get(1);
        String dni = customer.getDni();

        Customer expected = customerService.findByDni(dni);
        if (expected == customer) {
            mockMvc.perform(get("/api/customers/searchByIdAccount/74470551")).andExpect(status().isOk());
        }
    }

    @Test
    void findByFirstname() throws Exception{
        Customer customer = customerList.get(1);
        String firstname = customer.getFirstName();

        List<Customer> expected = customerService.findByFirstName(firstname);
        if (expected.size() > 0) {
            mockMvc.perform(get("/api/customers/searchFirstname/Julissa")).andExpect(status().isOk());
        }
    }

    @Test
    void findByLastname() throws Exception{
        Customer customer = customerList.get(1);
        String lastname = customer.getLastName();

        List<Customer> expected = customerService.findByLastName(lastname);
        if (expected.size() > 0) {
            mockMvc.perform(get("/api/customers/searchLastname/Ponte")).andExpect(status().isOk());
        }
    }

    @Test
    void findByFirstnameAndLastname() throws Exception{
        Customer customer = customerList.get(1);
        String firstname = customer.getFirstName();
        String lastname = customer.getLastName();

        List<Customer> expected = customerService.findByFirstNameAndLastName(firstname, lastname);
        if (expected.size() > 0) {
            mockMvc.perform(get("/api/customers/searchFirstnameAndLastname/Julissa/Ponte")).andExpect(status().isOk());
        }
    }
}
