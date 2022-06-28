package com.appdhome.services;

import com.appdhome.entities.Employee;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService extends CrudService<Employee> {
    public Optional<Employee> findByDni(String dni) throws Exception;
    public List<Employee> findByFirstName(String firstname)throws Exception;
    public List<Employee> findByLastName(String lastname)throws Exception;
    public List<Employee> findByLastNameAndFirstName(String lastname,String firstname)throws Exception;
    public Optional<Employee> findByEmail(String email) throws Exception;
    public Optional<Employee> findByAccountId(Long id) throws Exception;
    public List<Employee> findByBirthday(String birthday)throws Exception;
}