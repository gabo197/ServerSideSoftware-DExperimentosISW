package com.appdhome.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Appointment.findByStatus", query = "select a from Appointment a where a.status = ?1")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="status", nullable = false, length = 50)
    private String status;
    @Column(name="description", nullable = false, length = 250)
    private String description;
    @Column(name="AppointmentDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @Column(name="address", nullable = false, length = 200)
    private String address;
    @Column(name="valorization", nullable = false, length = 200)
    private int valorization;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentMethod_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PaymentMethod paymentMethod;

}