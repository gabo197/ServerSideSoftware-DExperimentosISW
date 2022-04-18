package com.appdhome.services;

import com.appdhome.entities.Employee;
import com.appdhome.services.CrudService;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService extends CrudService<Employee> {
    public Employee findByDni(String dni) throws Exception;
    public List<Employee> findByLastName(String lastname)throws Exception;
    public List<Employee> findByLastNameAndFirstName(String lastname,String firstname)throws Exception;
    public List<Employee> findByFirstName(String firstname)throws Exception;
    public Optional<Employee> findByEmail(String email) throws Exception;
    public Optional<Employee> findByIdAccount(Long id) throws Exception;
}
