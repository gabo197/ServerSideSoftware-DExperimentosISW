package com.appdhome.controller;

import com.appdhome.entities.City;
import com.appdhome.services.impl.CityServiceImpl;
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

@WebMvcTest(controllers=CityController.class)
@ActiveProfiles("test")
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CityServiceImpl cityService;

    private List<City> cityList;

    // carga de data
    @BeforeEach
    void setUp(){
        cityList=new ArrayList<>();
        cityList.add(new City(1L,"Lima"));
        cityList.add(new City(1L,"Ica"));
        cityList.add(new City(1L,"Piura"));
        cityList.add(new City(1L,"Arequipa"));
    }
    @Test
    void findAllCity() throws Exception {
        given(cityService.getAll()).willReturn(cityList);
        mockMvc.perform(get("/api/city")).andExpect(status().isOk());
    }

    @Test
    void findCityById() throws Exception{
        Long CityId=1L;
        City city= new City(1L,"Lima");

        given(cityService.getById(CityId)).willReturn(Optional.of(city));
        mockMvc.perform(get("/api/city/{id}",city.getId())).andExpect(status().isOk());
    }
}
