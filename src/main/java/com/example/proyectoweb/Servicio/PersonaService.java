package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.PersonaDto;
import com.example.proyectoweb.Modelo.Persona;
import com.example.proyectoweb.Repo.RepoPersona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaService {

    private final RepoPersona repo;
    private final ModelMapper mapper;

    @Autowired
    public PersonaService(RepoPersona repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    // ==== CRUD básico ====

    public PersonaDto crear(PersonaDto dto) {
        Persona e = mapper.map(dto, Persona.class);
        e = repo.save(e);
        return mapper.map(e, PersonaDto.class);
    }

    public Optional<PersonaDto> obtenerPorId(Long id) {
        return repo.findById(id).map(e -> mapper.map(e, PersonaDto.class));
    }

    public List<PersonaDto> listar() {
        List<PersonaDto> out = new ArrayList<>();
        for (Persona e : repo.findAll()) {
            out.add(mapper.map(e, PersonaDto.class));
        }
        return out;
    }

    public Optional<PersonaDto> actualizar(Long id, PersonaDto dto) {
        return repo.findById(id).map(existing -> {
            Persona incoming = mapper.map(dto, Persona.class);
            incoming.setId(existing.getId());
            Persona saved = repo.save(incoming);
            return mapper.map(saved, PersonaDto.class);
        });
    }

    public boolean eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // ==== extra útil ====
    public Optional<PersonaDto> obtenerPorEmail(String email) {
        return repo.findAll().stream()
                .filter(p -> email != null && email.equals(p.getEmail()))
                .findFirst()
                .map(p -> mapper.map(p, PersonaDto.class));
    }
}
