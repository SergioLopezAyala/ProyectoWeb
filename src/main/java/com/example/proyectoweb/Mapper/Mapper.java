package com.example.proyectoweb.Mapper;

import com.example.proyectoweb.Dto.OrganizationDto;
import com.example.proyectoweb.Modelo.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper mapper;

    // Inyección del bean global definido en ModelMapperConfig
    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    // Mantengo exactamente tu nombre de método
    public OrganizationDto ConvertirOrg(Organization org) {
        if (org == null) return null;
        return mapper.map(org, OrganizationDto.class);
    }

    // Si también quieres el camino inverso:
    public Organization ConvertirOrg(OrganizationDto dto) {
        if (dto == null) return null;
        return mapper.map(dto, Organization.class);
    }
}
