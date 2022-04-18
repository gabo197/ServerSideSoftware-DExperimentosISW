package com.appdhome.service;

import com.appdhome.entities.Specialty;
import com.appdhome.repository.ISpecialtyRepository;
import com.appdhome.services.impl.SpecialtyServiceImpl;
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
public class SpecialtyServiceImplTest {

    @Mock
    private ISpecialtyRepository specialtyRepository;
    @InjectMocks
    private SpecialtyServiceImpl specialtyService;

    @Test
    public void saveTest() {
        Specialty specialty = new Specialty(1L, "Carpinteria");

        given(specialtyRepository.save(specialty)).willReturn(specialty);

        Specialty savedSpecialty = null;
        try {
            savedSpecialty = specialtyRepository.save(specialty);
        } catch (Exception e) {
        }

        assertThat(savedSpecialty).isNotNull();
        verify(specialtyRepository).save(any(Specialty.class));
    }
    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;
        Specialty specialty = new Specialty(1L, "Carpinteria");

        given(specialtyRepository.findById(id)).willReturn(Optional.of(specialty));

        Optional<Specialty> expected = null;
        expected = specialtyService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<Specialty> list = new ArrayList<>();
        list.add(new Specialty(1L, "Carpinteria"));
        list.add(new Specialty(1L, "Electrodomesticos"));
        list.add(new Specialty(1L, "Cerrajeria"));
        list.add(new Specialty(1L, "Lavanderia"));
        given(specialtyRepository.findAll()).willReturn(list);
        List<Specialty> expected = specialtyService.getAll();
        assertEquals(expected, list);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        specialtyService.delete(id);
        verify(specialtyRepository, times(1)).deleteById(id);
    }
}
