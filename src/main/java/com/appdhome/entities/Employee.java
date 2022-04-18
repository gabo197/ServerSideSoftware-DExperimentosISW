package com.appdhome.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;


@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Employee.findByFirstName",query="Select c from Customer c where c.firstName=?1")

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;
    //@Column(name = "address", nullable = true, length = 150)
    //private String address;
    @Column(name = "cellphone", nullable = false, length = 9)
    private String cellphone;
    @Column(name = "email", nullable = true, length = 50, unique = true)
    private String email;
    //@Column(name = "username", nullable = true, length = 50)
    //private String username;
    //@Column(name="password",nullable = false, length = 15)
    //private String password;
    @Column(name="birthday",nullable = false)
    private String birthday;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Specialty specialty;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private District district;


}
