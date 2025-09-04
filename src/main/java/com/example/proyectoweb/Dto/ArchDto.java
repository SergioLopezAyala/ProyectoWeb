package com.example.proyectoweb.Dto;

import com.example.proyectoweb.Modelo.Actividad;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class ArchDto {
    private Long id;
    private Actividad actividadI;
    private Actividad actividadD;

}
