package com.example.proyectoweb.Modelo;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Gateway")
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type", nullable=false, length=100)
    private String type;
    @OneToMany
    @Column(name = "archs")
    private List<Arch> archs;

    public Gateway(Long id, String type) {
        this.id = id;
        this.type = type;
        this.archs = new ArrayList<>();
    }
}

