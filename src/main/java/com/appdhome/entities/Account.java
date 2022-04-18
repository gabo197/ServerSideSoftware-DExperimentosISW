package com.appdhome.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
// en el diagrama de base de datos tiene que tener la tabla account el username y no la tabla
@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Column(name="password",nullable = false, length = 15)
    private String password;
    @Column(name="userType",nullable = true, length = 15)
    private Integer userType;
    @Column(name="verify",nullable = true, length = 15)
    private Boolean verify;
}
