package com.appdhome.controller;

import com.appdhome.entities.*;
import com.appdhome.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers=EmployeeController.class)
@ActiveProfiles("test")

public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceImpl employeeService;
    private List<Employee> employeeList;

    Account account= new Account();
    Specialty specialty=new Specialty();
    District district= new District();

    @BeforeEach
    void SetUp(){
        employeeList=new ArrayList<>();
        employeeList.add(new Employee(1L ,"Katerin","Coronel","7236238","928392","katy@gmail.com","1-1-2002",account,specialty,district));
        employeeList.add(new Employee(2L ,"Gianella","Coronel","7238238","928391","giane@gmail.com","1-1-2002",account,specialty,district));
        employeeList.add(new Employee(3L ,"Julissa","Ponte","74470551","987654321","uli@hotmail.com","01-01-2000",
                new Account(3L, "juliT", "654322", 2, true ),specialty,district));
    }

//    US01 Como cliente quiero visualizar la lista de los trabajadores en la plataforma para conocer información relevante de ellos.
    @Test
    void findAll() throws Exception{
        given(employeeService.getAll()).willReturn(employeeList);
        mockMvc.perform(get("/api/employees")).andExpect(status().isOk());
    }
//    US19: Como trabajador quiero visualizar mi perfil para verificar si mis datos son correctos.
    @Test
    void findById() throws Exception{
        Employee employee = employeeList.get(2);
        Long id = employee.getId();

        Optional<Employee> expected = employeeService.getById(id);
        if (expected.isPresent()) {
            mockMvc.perform(get("/api/employees/1")).andExpect(status().isOk());
        }
    }
    @Test
    void findByAccountId() throws Exception{
        Employee employee = employeeList.get(2);
        Long accountId = employee.getAccount().getId();

        Optional<Employee> expected = employeeService.findByIdAccount(accountId);
        if (expected.isPresent()) {
            mockMvc.perform(get("/api/employees/searchByIdAccount/2")).andExpect(status().isOk());
        }
    }
}

