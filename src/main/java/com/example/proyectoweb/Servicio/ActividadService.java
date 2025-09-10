package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.ActivityDto;
import com.example.proyectoweb.Modelo.Actividad;
import com.example.proyectoweb.Repo.RepoActividad;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActividadService {

    private final RepoActividad repo;
    private final ModelMapper mapper;

    @Autowired
    public ActividadService(RepoActividad repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ActivityDto crear(ActivityDto dto) {
        Actividad e = mapper.map(dto, Actividad.class);
        e = repo.save(e);
        return mapper.map(e, ActivityDto.class);
    }

    public Optional<ActivityDto> obtenerPorId(Long id) {
        return repo.findById(id).map(e -> mapper.map(e, ActivityDto.class));
    }

    public List<ActivityDto> listar() {
        List<ActivityDto> out = new ArrayList<>();
        for (Actividad e : repo.findAll()) {
            out.add(mapper.map(e, ActivityDto.class));
        }
        return out;
    }

    public Optional<ActivityDto> actualizar(Long id, ActivityDto dto) {
        return repo.findById(id).map(existing -> {
            Actividad incoming = mapper.map(dto, Actividad.class);
            incoming.setId(existing.getId());
            Actividad saved = repo.save(incoming);
            return mapper.map(saved, ActivityDto.class);
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
