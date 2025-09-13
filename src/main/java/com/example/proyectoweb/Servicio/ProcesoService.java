package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.ProcesoDto;
import com.example.proyectoweb.Modelo.Proceso;
import com.example.proyectoweb.Repo.RepoProceso;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcesoService {

    private final RepoProceso repo;
    private final ModelMapper mapper;

    public ProcesoDto crear(ProcesoDto dto) {
        Proceso entity = mapper.map(dto, Proceso.class);
        entity = repo.save(entity);
        return mapper.map(entity, ProcesoDto.class);
    }

    public Optional<ProcesoDto> obtener(Long id) {
        return repo.findById(id).map(e -> mapper.map(e, ProcesoDto.class));
    }

    public List<ProcesoDto> listar() {
        List<ProcesoDto> out = new ArrayList<>();
        for (Proceso e : repo.findAll()) {
            out.add(mapper.map(e, ProcesoDto.class));
        }
        return out;
    }

    public Optional<ProcesoDto> actualizar(Long id, ProcesoDto dto) {
        return repo.findById(id).map(existing -> {
            Proceso incoming = mapper.map(dto, Proceso.class);
            incoming.setId(existing.getId());
            Proceso saved = repo.save(incoming);
            return mapper.map(saved, ProcesoDto.class);
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
