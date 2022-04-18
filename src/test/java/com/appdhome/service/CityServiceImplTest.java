package com.appdhome.service;

import com.appdhome.entities.City;
import com.appdhome.repository.ICityRepository;
import com.appdhome.services.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
public class CityServiceImplTest {

    @Mock
    private ICityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    public void saveTest() {
        City city = new City(1L, "Lima");

        given(cityRepository.save(city)).willReturn(city);

        City savedCity = null;
        try {
            savedCity = cityRepository.save(city);
        } catch (Exception e) {
        }

        assertThat(savedCity).isNotNull();
        verify(cityRepository).save(any(City.class));
    }
    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;
        City city = new City(1L, "Lima");

        given(cityRepository.findById(id)).willReturn(Optional.of(city));

        Optional<City> expected = null;
        expected = cityService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<City> list = new ArrayList<>();
        list.add(new City(1L, "Lima"));
        list.add(new City(1L, "Ica"));
        list.add(new City(1L, "Piura"));
        list.add(new City(1L, "Arequipa"));
        given(cityRepository.findAll()).willReturn(list);
        List<City> expected = cityService.getAll();
        assertEquals(expected, list);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        cityService.delete(id);
        verify(cityRepository, times(1)).deleteById(id);
    }
}
