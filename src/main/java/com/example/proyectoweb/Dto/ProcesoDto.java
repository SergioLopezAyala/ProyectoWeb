package com.example.proyectoweb.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Boolean status;
}
