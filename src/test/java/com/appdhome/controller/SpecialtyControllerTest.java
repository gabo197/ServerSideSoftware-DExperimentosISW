package com.appdhome.controller;

import com.appdhome.entities.Specialty;
import com.appdhome.services.impl.SpecialtyServiceImpl;
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


@WebMvcTest(controllers=SpecialtyController.class)
@ActiveProfiles("test")
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SpecialtyServiceImpl specialtyService;

    private List<Specialty> specialtyList;

    // carga de data
    @BeforeEach
    void setUp(){
        specialtyList=new ArrayList<>();
        specialtyList.add(new Specialty(1L,"Carpinteria"));
        specialtyList.add(new Specialty(1L,"Electrodomesticos"));
        specialtyList.add(new Specialty(1L,"Cerrajeria"));
        specialtyList.add(new Specialty(1L,"Lavanderia"));
    }
    @Test
    void findAllSpecialty() throws Exception {
        given(specialtyService.getAll()).willReturn(specialtyList);
        mockMvc.perform(get("/api/specialty")).andExpect(status().isOk());
    }

    @Test
    void findSpecialtyById() throws Exception{
        Long SpecialtyId=1L;
        Specialty specialty= new Specialty(1L,"Carpinteria");

        given(specialtyService.getById(SpecialtyId)).willReturn(Optional.of(specialty));
        mockMvc.perform(get("/api/specialty/{id}",specialty.getId())).andExpect(status().isOk());
    }


}
