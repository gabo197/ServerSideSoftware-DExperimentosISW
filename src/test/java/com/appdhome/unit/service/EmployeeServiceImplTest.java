package com.appdhome.unit.service;

import com.appdhome.entities.*;
import com.appdhome.repository.IEmployeeRepository;
import com.appdhome.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
    Account account;
//Necessary
    Specialty specialty;
    District district;
    Employee employee;
    @BeforeEach
    void setUp() {
        account = new Account(1L,"julissaponteT","julissaponteT",2,true);
        specialty = new Specialty(1L,"Carpintería");
        district = new District(1L,"Cercado de Lima", new City(1L,"Lima"));
        employee = new Employee(1L,"Julissa","Ponte","76543210","987654321","julissaponte@gmail.com","female", account, specialty, district);
    }
    @Test
    public void saveTest() {
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(employee);
        }
        catch (Exception ignored) {
        }

        assertThat(savedEmployee).isNotNull();
        verify(employeeRepository).save(any(Employee.class));
    }
    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;

        given(employeeRepository.findById(id)).willReturn(Optional.of(employee));

        Optional<Employee> expected;
        expected = employeeService.getById(id);
        assertThat(expected).isNotNull();
    }
    @Test
    void findAllTest() throws Exception {
        Account account2 = new Account(2L,"julesponte","julesponte",2,true);
        Employee employee2 = new Employee(2L,"Jules","Ponte","76543211","997654321","julesponte@gmail.com","female", account2, specialty, district);

        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
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

        given(employeeRepository.findByDni(dni)).willReturn(Optional.of(employee));

        Optional<Employee> expected;
        expected = employeeService.findByDni(dni);
        assertThat(expected).isNotNull();
    }
    @Test
    void findByFirstNameTest() throws Exception {
        String firstname = "Julissa";
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        given(employeeRepository.findByFirstName(firstname)).willReturn(list);

        List<Employee> expected = employeeService.findByFirstName(firstname);
        assertEquals(expected, list);
    }
    @Test
    void findByLastNameTest() throws Exception {
        String lastname = "Ponte";
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        given(employeeRepository.findByLastName(lastname)).willReturn(list);

        List<Employee> expected = employeeService.findByLastName(lastname);
        assertEquals(expected, list);
    }
    @Test
    void findByLastNameAndFirstNameTest() throws Exception {
        String firstname = "Julissa";
        String lastname = "Ponte";
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        given(employeeRepository.findByLastNameAndFirstName(lastname, firstname)).willReturn(list);

        List<Employee> expected = employeeService.findByLastNameAndFirstName(lastname, firstname);
        assertEquals(expected, list);
    }
    @Test
    void findByEmailTest() throws Exception {
        String email = "julissaponte@gmail.com";

        given(employeeRepository.findByEmail(email)).willReturn(Optional.of(employee));

        Optional<Employee> expected;
        expected = employeeService.findByEmail(email);
        assertThat(expected).isNotNull();
    }
    @Test
    void findByAccountId() throws Exception {
        Long accountId = 1L;

        given(employeeRepository.findByAccountId(1L)).willReturn(Optional.of(employee));

        Optional<Employee> expected;
        expected = employeeService.findByAccountId(accountId);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByGenderTest() throws Exception {
        String birthday = "female";
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        given(employeeRepository.findByBirthday(birthday)).willReturn(list);

        List<Employee> expected = employeeService.findByBirthday(birthday);
        assertEquals(expected, list);
    }
}