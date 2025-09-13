package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.PersonaDto;
import com.example.proyectoweb.Modelo.Persona;
import com.example.proyectoweb.Repo.RepoPersona;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final RepoPersona repo;
    private final ModelMapper mapper;

    public PersonaDto crear(PersonaDto dto) {
        Persona e = mapper.map(dto, Persona.class);
        e = repo.save(e);
        return mapper.map(e, PersonaDto.class);
    }

    public Optional<PersonaDto> obtener(Long id) {
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

    // Para login/lookup por email (útil para AuthController)
    public Optional<PersonaDto> obtenerPorEmail(String email) {
        // Opción A (rápida, sin modificar el repo):
        return repo.findAll().stream()
                .filter(p -> p.getEmail() != null && p.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .map(p -> mapper.map(p, PersonaDto.class));
    }
}
