package com.appdhome.controller;

import com.appdhome.entities.*;
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
    Account account = new Account();
    District district = new District();
    Customer customer = new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com", "998745247", account, district);
    Employee employee = new Employee();
    PaymentMethod paymentMethod = new PaymentMethod();

    Appointment appointment = new Appointment();

    @BeforeEach
    void setUp(){
        appointmentList=new ArrayList<>();
        appointmentList.add(new Appointment(1L, "pendiente", "desperfecto en mi cocina, no prenden las hornillas", dateD, "Avenida Abancay", 5, customer, employee, paymentMethod));
        appointmentList.add(new Appointment(2L, "pendiente", "desperfecto en mi lavadora, hace ruido", dateD, "Avenida Aviación", 4, customer, employee, paymentMethod));
    }

///   US05: Como cliente quiero contratar a un trabajador para arreglar el desperfecto de mi hogar.
    @Test
    void Hire() throws Exception{
        given(appointmentService.getAll()).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointments")).andExpect(status().isOk());
    }

///    US06: Como cliente quiero visualizar las citas que he reservado para tener un control de ellas.
    @Test
    void CheckCustomer() throws Exception{
        List<Appointment> appointmentListP = appointmentService.findByIdCustomer(1L);
        if (appointmentList.get(1) == appointmentListP){
            mockMvc.perform(get("/api/appointments/searchByIdCustomer/1")).andExpect(status().isOk());
        }
    }
///  US13:    Como trabajador quiero visualizar una solicitud de trabajo a detalle para llegar a tiempo al lugar establecido.
    @Test
    void CheckAppointment_Employee() throws Exception{
        List<Appointment> appointments = appointmentService.getAll();
        if (appointmentList.get(1) == appointmentList){
            mockMvc.perform(get("/api/appointments/1")).andExpect(status().isOk());
        }
    }
}
