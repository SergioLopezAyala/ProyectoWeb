package com.example.proyectoweb.Modelo;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable=false, length=100)
    private String name;
    @Column(name = "description", nullable=false, length=1000)
    private String description;
}
