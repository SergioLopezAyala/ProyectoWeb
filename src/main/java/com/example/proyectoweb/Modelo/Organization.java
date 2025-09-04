package com.example.proyectoweb.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable=false, length=100)
    private String name;
    @Column(name = "NIT", nullable=false, length=100)
    private String nit;
    @Column(name = "email", nullable=false, length=100)
    private String email;

    public Organization(Long id, String name, String nit, String email) {
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.email = email;
    }
}
