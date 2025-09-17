package com.example.proyectoweb.Dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayDto {
    private String type;
    private List<Long> archIds;
}
