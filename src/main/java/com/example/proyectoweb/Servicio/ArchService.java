package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.ArchDto;
import com.example.proyectoweb.Modelo.Arch;
import com.example.proyectoweb.Repo.RepoArch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArchService {

    private final RepoArch repo;
    private final ModelMapper mapper;

    @Autowired
    public ArchService(RepoArch repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ArchDto crear(ArchDto dto) {
        Arch e = mapper.map(dto, Arch.class);
        e = repo.save(e);
        return mapper.map(e, ArchDto.class);
    }

    public Optional<ArchDto> obtenerPorId(Long id) {
        return repo.findById(id).map(e -> mapper.map(e, ArchDto.class));
    }

    public List<ArchDto> listar() {
        List<ArchDto> out = new ArrayList<>();
        for (Arch e : repo.findAll()) {
            out.add(mapper.map(e, ArchDto.class));
        }
        return out;
    }

    public Optional<ArchDto> actualizar(Long id, ArchDto dto) {
        return repo.findById(id).map(existing -> {
            Arch incoming = mapper.map(dto, Arch.class);
            incoming.setId(existing.getId());
            Arch saved = repo.save(incoming);
            return mapper.map(saved, ArchDto.class);
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
