package com.appdhome.controller;

import com.appdhome.entities.Account;
import com.appdhome.entities.District;
import com.appdhome.entities.Employee;
import com.appdhome.entities.Specialty;
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
        employeeList.add(new Employee(2L ,"Katerin","Coronel","7236238","928392","katy@gmail.com","1-1-2002",account,specialty,district));
        employeeList.add(new Employee(2L ,"Gianella","Coronel","7238238","928391","giane@gmail.com","1-1-2002",account,specialty,district));
    }

    @Test
    void CheckCustomers() throws Exception{
        given(employeeService.getAll()).willReturn(employeeList);
        mockMvc.perform(get("/api/employees")).andExpect(status().isOk());
    }


}

