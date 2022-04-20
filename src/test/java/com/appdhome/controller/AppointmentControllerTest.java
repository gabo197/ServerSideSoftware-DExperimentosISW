package com.appdhome.controller;
//US05: Como cliente quiero contratar a un trabajador para arreglar el desperfecto de mi hogar.
import com.appdhome.entities.Appointment;
import com.appdhome.entities.Customer;
import com.appdhome.entities.Employee;
import com.appdhome.entities.PaymentMethod;
import com.appdhome.services.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AppointmentController.class)
@ActiveProfiles("test")
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AppointmentServiceImpl appointmentService;

    private List<Appointment> appointmentList;
    Date dateD = new Date();
    Customer customer = new Customer();
    Employee employee = new Employee();
    PaymentMethod paymentMethod = new PaymentMethod();
    // carga de data
    @BeforeEach
    void setUp(){
        appointmentList=new ArrayList<>();
        appointmentList.add(new Appointment(1L, "pendiente", "desperfecto en mi cocina, no prenden las hornillas",
                dateD, "Avenida Abancay", 5, customer, employee, paymentMethod));

    }

    @Test
    void Hire() throws Exception{
        given(appointmentService.getAll()).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointments")).andExpect(status().isOk());
    }

}
