package com.example.proyectoweb.Modelo;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Activity")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable=false, length=100)
    private String name;
    @Column(name = "type", nullable=false, length=100)
    private String type;
    @Column(name = "description", nullable=false, length=1000)
    private String descripcion;

    public Actividad(Long id, String name, String type, String descripcion) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.descripcion = descripcion;
    }
}
