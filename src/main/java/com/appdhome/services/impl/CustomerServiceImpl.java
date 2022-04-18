package com.appdhome.services.impl;

import com.appdhome.entities.Customer;
import com.appdhome.repository.ICustomerRepository;
import com.appdhome.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer save(Customer customer) throws Exception{
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception{
        customerRepository.deleteById(id);
    }
    @Override
    public List<Customer> getAll() throws Exception{
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByFirstName(String firstname) throws Exception {
        return customerRepository.findByFirstName(firstname);
    }

    @Override
    public List<Customer> findByLastName(String lastname) throws Exception {
        return customerRepository.findByLastName(lastname);
    }

    @Override
    public List<Customer> findByFirstNameAndLastName(String firstname, String lastname) throws Exception {
        return customerRepository.findByFirstNameAndLastName(firstname, lastname);
    }

    @Override
    public Customer findByDni(String dni) throws Exception {
        return customerRepository.findByDni(dni);
    }

    @Override
    public Optional<Customer> findByEmail(String email) throws Exception {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByIdAccount(Long id) throws Exception {
        return customerRepository.findByAccount_Id(id);
    }

}
