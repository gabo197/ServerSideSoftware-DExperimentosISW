package com.appdhome.unit.service;

import com.appdhome.entities.Account;
import com.appdhome.entities.Customer;
import com.appdhome.entities.District;
import com.appdhome.repository.ICustomerRepository;
import com.appdhome.services.impl.CustomerServiceImpl;
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
import static org.postgresql.hostchooser.HostRequirement.any;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private ICustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    Account account = new Account();
    District district = new District();

    @Test
    public void saveTest(){
        Customer customer = new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district);
        given(customerRepository.save(customer)).willReturn(customer);
        Customer savedCustomer = null;
        try {
            savedCustomer = customerRepository.save(customer);
        }catch (Exception e){
        }

        assertThat(savedCustomer).isNotNull();
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        customerService.delete(id);
        verify(customerRepository, times(1)). deleteById(id);
    }

    @Test
    void getAllTest() throws Exception{
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district));
        list.add(new Customer(2L, "Katerin", "Villalobos", "78858545","katy@hotmail.com",
                "97374520", account, district));

        given(customerRepository.findAll()).willReturn(list);
        List<Customer> expected = customerService.getAll();
        assertEquals(expected, list);
    }

    @Test
    void getByIdTest() throws Exception {
        Long id = 1L;
        Customer customer = new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district);

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));
        Optional<Customer> expected = null;
        expected = customerService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByFirstNameTest() throws Exception {
        String firstName = "Milagros";
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district));
        list.add(new Customer(2L, "Katerin", "Villalobos", "78858545","katy@hotmail.com",
                "97374520", account, district));

        given(customerRepository.findByFirstName(firstName)).willReturn(list);
        String expected = null;
        expected = String.valueOf(customerService.findByFirstName(firstName));
        assertThat(expected).isNotNull();
    }

    @Test
    void findByLastNameTest() throws Exception {
        String lastName = "Villalobos";
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district));
        list.add(new Customer(2L, "Katerin", "Villalobos", "78858545","katy@hotmail.com",
                "97374520", account, district));

        given(customerRepository.findByLastName(lastName)).willReturn(list);
        String expected = null;
        expected = String.valueOf(customerService.findByLastName(lastName));
        assertThat(expected).isNotNull();
    }

    @Test
    void findByFirstNameAndLastNameTest() throws Exception {
        String firstName = "Milagros";
        String lastName = "Sotomayor";
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district));
        list.add(new Customer(2L, "Katerin", "Villalobos", "78858545","katy@hotmail.com",
                "97374520", account, district));

        given(customerRepository.findByFirstNameAndLastName(firstName,lastName)).willReturn(list);
        String expected = null;
        expected = String.valueOf(customerService.findByFirstNameAndLastName(firstName, lastName));
        assertThat(expected).isNotNull();
    }

    @Test
    void findByDniTest() throws Exception {
        String dni = "78478541";
        Customer customer = new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district);

        given(customerRepository.findByDni(dni)).willReturn(customer);
        String expected = null;
        expected = String.valueOf(customerService.findByDni(dni));
        assertThat(expected).isNotNull();
    }

    @Test
    void findByEmailTest() throws Exception {
        String email = "mili@hotmail.com";
        Customer customer = new Customer(1L, "Milagros", "Sotomayor", "78478541", "mili@hotmail.com",
                "998745247", account, district);

        given(customerRepository.findByEmail(email)).willReturn(Optional.of(customer));
        Optional<Customer> expected = null;
        expected = customerService.findByEmail(email);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByIdAccountTest() throws Exception {
        Long id = 2L;
        Customer customer = new Customer(3L, "Julissa", "Ponte", "74470551", "juli@hotmail.com",
                "987654321", new Account(2L, "juli", "654321", 1, true ), district);

        given(customerRepository.findByAccount_Id(id)).willReturn(Optional.of(customer));
        Optional<Customer> expected = null;
        expected = customerService.findByIdAccount(id);
        assertThat(expected).isNotNull();
    }


}
