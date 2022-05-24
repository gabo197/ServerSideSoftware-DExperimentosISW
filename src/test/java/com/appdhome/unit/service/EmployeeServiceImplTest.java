package com.appdhome.unit.service;

import com.appdhome.entities.*;
import com.appdhome.repository.IEmployeeRepository;
import com.appdhome.services.impl.EmployeeServiceImpl;
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
public class EmployeeServiceImplTest {
    @Mock
    private IEmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Test
    public void saveTest() {
        Account account = new Account(1L,"julissaponteT","julissaponteT",2,true);
        Specialty specialty = new Specialty(1L, "Carpintería");
        District district = new District(1L, "Cercado de Lima", new City(1L, "Lima"));
        Employee employee = new Employee(
                1L,
                "Julissa",
                "Ponte",
                "76543210",
                "987654321",
                "julissaponte@gmail.com",
                "02-02-2000",
                account,
                specialty,
                district);

        given(employeeRepository.save(employee)).willReturn(employee);

        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(employee);
        }
        catch (Exception e) {
        }

        assertThat(savedEmployee).isNotNull();
        verify(employeeRepository).save(any(Employee.class));
    }
    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;
        Account account = new Account(1L,"julissaponte","julissaponte",2,true);
        Specialty specialty = new Specialty(1L, "Carpintería");
        District district = new District(1L, "Cercado de Lima", new City(1L, "Lima"));
        Employee employee = new Employee(
                1L,
                "Julissa",
                "Ponte",
                "76543210",
                "987654321",
                "julissaponte@gmail.com",
                "02-02-2000",
                account,
                specialty,
                district);

        given(employeeRepository.findById(id)).willReturn(Optional.of(employee));

        Optional<Employee> expected = null;
        expected = employeeService.getById(id);
        assertThat(expected).isNotNull();
    }
    @Test
    void findAllTest() throws Exception {
        Account account = new Account(1L,"julissaponte","julissaponte",2,true);
        Account account2 = new Account(2L,"julesponte","julesponte",2,true);
        Specialty specialty = new Specialty(1L, "Carpintería");
        District district = new District(1L, "Cercado de Lima", new City(1L, "Lima"));

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(
                1L,
                "Julissa",
                "Ponte",
                "76543210",
                "987654321",
                "julissaponte@gmail.com",
                "02-02-2000",
                account,
                specialty,
                district));
        list.add(new Employee(
                2L,
                "Jules",
                "Ponte",
                "76543211",
                "997654321",
                "julesponte@gmail.com",
                "03-03-2000",
                account2,
                specialty,
                district));
        given(employeeRepository.findAll()).willReturn(list);
        List<Employee> expected = employeeService.getAll();
        assertEquals(expected, list);
    }
    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        employeeService.delete(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
    @Test
    void findByDniTest() throws Exception {
        String dni = "76543210";
        Account account = new Account(1L,"julissaponte","julissaponte",2,true);
        Specialty specialty = new Specialty(1L, "Carpintería");
        District district = new District(1L, "Cercado de Lima", new City(1L, "Lima"));
        Employee employee = new Employee(
                1L,
                "Julissa",
                "Ponte",
                "76543210",
                "987654321",
                "julissaponte@gmail.com",
                "02-02-2000",
                account,
                specialty,
                district);

        given(employeeRepository.findByDni(dni)).willReturn(Optional.of(employee));

        Optional<Employee> expected = null;
        expected = employeeService.findByDni(dni);
        assertThat(expected).isNotNull();
    }
}
