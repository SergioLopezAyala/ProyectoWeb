package com.example.proyectoweb.Modelo;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable=false, length=100)
    private String name;
    @Column(name = "email", nullable=false, length=100)
    private String email;
    @Column(name = "password", nullable=false, length=100)
    private String password;


    public Persona(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
