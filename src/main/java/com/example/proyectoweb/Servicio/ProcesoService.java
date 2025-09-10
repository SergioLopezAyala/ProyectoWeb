package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.ProcesoDto;
import com.example.proyectoweb.Modelo.Proceso;
import com.example.proyectoweb.Repo.RepoProceso;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProcesoService {

    private final RepoProceso repo;
    private final ModelMapper mapper;

    @Autowired
    public ProcesoService(RepoProceso repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ProcesoDto crear(ProcesoDto dto) {
        Proceso e = mapper.map(dto, Proceso.class);
        e = repo.save(e);
        return mapper.map(e, ProcesoDto.class);
    }

    public Optional<ProcesoDto> obtenerPorId(Long id) {
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
