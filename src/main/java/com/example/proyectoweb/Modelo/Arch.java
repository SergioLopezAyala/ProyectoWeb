package com.example.proyectoweb.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Arch")
public class Arch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "actividadI")
    private Actividad actividadI;

    @OneToOne
    @JoinColumn(name = "actividadD")
    private Actividad actividadD;

    public Arch(Long id, Actividad actividadI, Actividad actividadD) {
        this.id = id;
        this.actividadI = actividadI;
        this.actividadD = actividadD;
    }
}
