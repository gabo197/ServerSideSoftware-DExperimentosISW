package com.appdhome.repository;

import com.appdhome.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
    public List<Appointment> findAppointmentByEmployeeId(Long id);
    public List<Appointment> findAppointmentByCustomerId(Long id);
    public List<Appointment> findAppointmentByStatus(String status);
}
