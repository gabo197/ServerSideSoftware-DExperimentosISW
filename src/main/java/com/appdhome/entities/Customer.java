package com.appdhome.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Customer.findByFirstName", query = "select c from Customer c where c.firstName = ?1")

public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;
    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;
    @Column(name = "cellphone", nullable = false, length = 60)
    private String cellphone;
    /*
    @Column(name = "username", nullable = false, length = 60)
    private String username;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private District district;
}