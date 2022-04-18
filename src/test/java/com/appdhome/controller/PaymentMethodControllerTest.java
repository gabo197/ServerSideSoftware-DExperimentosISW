package com.appdhome.controller;

import com.appdhome.entities.PaymentMethod;
import com.appdhome.services.impl.PaymentMethodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest(controllers=PaymentMethodController.class)
@ActiveProfiles("test")
public class PaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PaymentMethodServiceImpl paymentMethodService;

    private List<PaymentMethod> paymentMethodList;

    // carga de data
    @BeforeEach
    void setUp(){
        paymentMethodList=new ArrayList<>();
        paymentMethodList.add(new PaymentMethod(1L,"Efectivo"));
        paymentMethodList.add(new PaymentMethod(1L,"Transferencia"));
        paymentMethodList.add(new PaymentMethod(1L,"Tarjeta de Debido"));
        paymentMethodList.add(new PaymentMethod(1L,"Tarjeta de Credito"));
    }
    @Test
    void findAllPaymentMethod() throws Exception {
        given(paymentMethodService.getAll()).willReturn(paymentMethodList);
        mockMvc.perform(get("/api/paymentMethod")).andExpect(status().isOk());
    }

    @Test
    void findPaymentMethodById() throws Exception{
        Long PaymentMethodId=1L;
        PaymentMethod PaymentMethod= new PaymentMethod(1L,"Efectivo");

        given(paymentMethodService.getById(PaymentMethodId)).willReturn(Optional.of(PaymentMethod));
        mockMvc.perform(get("/api/paymentMethod/{id}",PaymentMethod.getId())).andExpect(status().isOk());
    }


}

