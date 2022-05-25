package com.appdhome.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name="usertype",nullable = true, length = 15)
    private Integer usertype;
    @Column(name="verify",nullable = true, length = 15)
    private Boolean verify;
}
