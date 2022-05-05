package com.appdhome.unit.controller;

import com.appdhome.controller.AccountController;
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
import java.util.Optional;

@WebMvcTest(controllers= AccountController.class)
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
        accountList.add(new Account(2L, "Gianella", "1234" , 1,true ));
        accountList.add(new Account(3L, "Flavio", "12345" , 1,true ));
        accountList.add(new Account(4L, "julissa", "123456", 2, true ));

    }
 // US09:    Como cliente quiero iniciar sesión para entrar a la aplicación.

    @Test
    void ValidateAccount()throws Exception{
        given(accountService.getAll()).willReturn(accountList);
        mockMvc.perform(get("/api/auth/account"));

    }

//    US18: Como trabajador quiero iniciar sesión en la aplicación para visualizar si tengo nuevas solicitudes de trabajo.
    @Test
    void LogIn() throws Exception{
        Account account = accountList.get(3);
        String username = account.getUsername();
        String password = account.getPassword();

        Optional<Account> expected = accountService.findByUsernameAndPassword(username, password);
        if (expected.isPresent()) {
            mockMvc.perform(get("/api/auth/account/login")).andExpect(status().isOk());
        }
    }

}
