package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.ActivityDto;
import com.example.proyectoweb.Modelo.Actividad;
import com.example.proyectoweb.Repo.RepoActividad;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActividadService {

    private final RepoActividad repo;
    private final ModelMapper mapper;

    public ActivityDto crear(ActivityDto dto) {
        Actividad entity = mapper.map(dto, Actividad.class);
        entity = repo.save(entity);
        return mapper.map(entity, ActivityDto.class);
    }

    public Optional<ActivityDto> obtener(Long id) {
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
