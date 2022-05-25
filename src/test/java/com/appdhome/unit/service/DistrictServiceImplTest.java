package com.appdhome.unit.service;

import com.appdhome.entities.City;
import com.appdhome.entities.District;
import com.appdhome.repository.IDistrictRepository;


import com.appdhome.services.impl.DistrictServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceImplTest {
    @Mock
    private IDistrictRepository districtRepository;
    @InjectMocks
    private DistrictServiceImpl districtService;
    @MockBean
    City city = new City();


    @Test
    public void saveTest() {
        District district = new District(1L, "La Molina", city);


        given(districtRepository.save(district)).willReturn(district);

        District savedDistrict = null;
        try {
            savedDistrict = districtRepository.save(district);
        }
        catch (Exception e) {
        }

        assertThat(savedDistrict).isNotNull();
        verify(districtRepository).save(any(District.class));
    }

    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;
        District district = new District(1L, "La Molina", city);

        given(districtRepository.findById(id)).willReturn(Optional.of(district));

        Optional<District> expected = null;
        expected = districtService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<District> list = new ArrayList<>();
        list.add(new District(1L, "La Molina", city));
        list.add(new District(1L, "Surco", city));
        list.add(new District(1L, "Jesus Maria", city));
        list.add(new District(1L, "San Isidro", city));
        list.add(new District(1L, "Los Olivos", city));
        given(districtRepository.findAll()).willReturn(list);
        List<District> expected = districtService.getAll();
        assertEquals(expected, list);
    }

    @Test
    void findByNameTest() throws Exception {
        String name = "La Molina";
        District district = new District(1L, "La Molina", city);

        given(districtRepository.findByName(name)).willReturn(district);

        Optional<District>
        expected = Optional.ofNullable(districtService.findByName(name));
        assertThat(expected).isNotNull();
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        districtService.delete(id);
        verify(districtRepository, times(1)).deleteById(id);
    }



}

