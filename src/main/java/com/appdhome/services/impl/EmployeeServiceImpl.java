package com.appdhome.services.impl;

import com.appdhome.entities.Employee;
import com.appdhome.repository.IEmployeeRepository;
import com.appdhome.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee save(Employee employee) throws Exception{
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception{
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() throws Exception{
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(Long id)  throws Exception{
        return employeeRepository.findById(id);
    }

    @Override
    public Employee findByDni(String dni) throws Exception{
        return employeeRepository.findByDni(dni);
    }

    @Override
    public List<Employee> findByLastName(String lastname) throws Exception{
        return employeeRepository.findByLastName(lastname);
    }

    @Override
    public List<Employee> findByLastNameAndFirstName(String lastname, String firstname) throws Exception{
        return employeeRepository.findByLastNameAndFirstName(lastname,firstname);
    }

    @Override
    public List<Employee> findByFirstName(String firstname) throws Exception{
        return employeeRepository.findByFirstName(firstname);
    }

    @Override
    public Optional<Employee> findByEmail(String email) throws Exception {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Optional<Employee> findByIdAccount(Long id) throws Exception {
        return employeeRepository.findByAccount_Id(id);
    }

}
