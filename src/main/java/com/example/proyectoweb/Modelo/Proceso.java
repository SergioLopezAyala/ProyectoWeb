package com.example.proyectoweb.Modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Proceso")
public class Proceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable=false, length=100)
    private String name;
    @Column(name = "description", nullable=false, length=1000)
    private String description;
    @Column(name = "category", nullable=false, length=100)
    private String category;
    @Column(name = "status", nullable=false)
    private Boolean status;
    @OneToMany
    @Column(name = "activities")
    private List<Actividad> activities;
    @OneToMany
    @Column(name = "archs")
    private List<Arch> archs;
    @OneToMany
    @Column(name = "gateways")
    private List<Gateway> gateways;

    public Proceso(Long id, String name, String description,
                   String category, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status;
        this.activities = new ArrayList<>();
        this.archs = new ArrayList<>();
        this.gateways = new ArrayList<>();
    }
}
