package com.appdhome.services;

import com.appdhome.entities.Appointment;

import java.util.List;

public interface IAppointmentService extends CrudService<Appointment>{
    public List<Appointment> findByIdCustomer(Long id) throws Exception;
    public List<Appointment> findByStatus(String status) throws Exception;
    public List<Appointment> findByIdEmployee(Long id) throws Exception;
}
