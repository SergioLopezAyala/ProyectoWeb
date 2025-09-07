package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.OrganizationDto;
import com.example.proyectoweb.Modelo.Organization;
import com.example.proyectoweb.Repo.RepoOrganization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrganizationService {

    private final RepoOrganization repo;
    private final ModelMapper mapper;

    @Autowired
    public OrganizationService(RepoOrganization repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    // ==== CRUD básico ====

    public OrganizationDto crear(OrganizationDto dto) {
        Organization e = mapper.map(dto, Organization.class);
        e = repo.save(e);
        return mapper.map(e, OrganizationDto.class);
    }

    public Optional<OrganizationDto> obtenerPorId(Long id) {
        return repo.findById(id).map(e -> mapper.map(e, OrganizationDto.class));
    }

    public List<OrganizationDto> listar() {
        List<OrganizationDto> out = new ArrayList<>();
        for (Organization e : repo.findAll()) {
            out.add(mapper.map(e, OrganizationDto.class));
        }
        return out;
    }

    public Optional<OrganizationDto> actualizar(Long id, OrganizationDto dto) {
        return repo.findById(id).map(existing -> {
            Organization incoming = mapper.map(dto, Organization.class);
            incoming.setId(existing.getId());
            Organization saved = repo.save(incoming);
            return mapper.map(saved, OrganizationDto.class);
        });
    }

    public boolean eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
