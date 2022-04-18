package com.appdhome.repository;

import com.appdhome.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    public List<Customer> findByFirstName(String firstname);
    public List<Customer> findByLastName(String lastname);
    public List<Customer> findByFirstNameAndLastName(String firstname, String lastname);
    public Customer findByDni(String dni);
    public Optional<Customer> findByEmail(String email);
    public Optional<Customer> findByAccount_Id(Long id);
}