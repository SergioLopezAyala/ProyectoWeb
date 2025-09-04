package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.OrganizationDto;
import com.example.proyectoweb.Modelo.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrgMapper {
    private final ModelMapper mapper;
    public OrgMapper(ModelMapper mapper) { this.mapper = mapper; }

    public OrganizationDto toDto(Organization org) {
        return mapper.map(org, OrganizationDto.class);
    }
}
