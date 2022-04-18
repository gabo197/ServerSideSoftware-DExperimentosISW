package com.appdhome.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="specialty")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Specialty implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "specialtyname", nullable = false, length = 50)
    private String name;

}