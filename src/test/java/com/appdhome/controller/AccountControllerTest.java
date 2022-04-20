package com.appdhome.controller;

import com.appdhome.entities.Account;

import com.appdhome.services.impl.AccountServiceImpl;

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

@WebMvcTest(controllers=AccountController.class)
@ActiveProfiles("test")

public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountServiceImpl accountService;

    private List<Account> accountList;




    @BeforeEach
    void setUp(){
        accountList=new ArrayList<>();
        accountList.add(new Account(1L, "Katerin", "123" , 1,true ));
        accountList.add(new Account(1L, "Gianella", "1234" , 1,true ));
        accountList.add(new Account(1L, "Flavio", "12345" , 1,true ));

    }
    @Test
    void ValidateAccount()throws Exception{
        given(accountService.getAll()).willReturn(accountList);
        mockMvc.perform(get("/api/auth/account"));

    }

}
