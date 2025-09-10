package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.GatewayDto;
import com.example.proyectoweb.Modelo.Gateway;
import com.example.proyectoweb.Repo.RepoGateway;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GatewayService {

    private final RepoGateway repo;
    private final ModelMapper mapper;

    @Autowired
    public GatewayService(RepoGateway repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public GatewayDto crear(GatewayDto dto) {
        Gateway e = mapper.map(dto, Gateway.class);
        e = repo.save(e);
        return mapper.map(e, GatewayDto.class);
    }

    public Optional<GatewayDto> obtenerPorId(Long id) {
        return repo.findById(id).map(e -> mapper.map(e, GatewayDto.class));
    }

    public List<GatewayDto> listar() {
        List<GatewayDto> out = new ArrayList<>();
        for (Gateway e : repo.findAll()) {
            out.add(mapper.map(e, GatewayDto.class));
        }
        return out;
    }

    public Optional<GatewayDto> actualizar(Long id, GatewayDto dto) {
        return repo.findById(id).map(existing -> {
            Gateway incoming = mapper.map(dto, Gateway.class);
            incoming.setId(existing.getId());
            Gateway saved = repo.save(incoming);
            return mapper.map(saved, GatewayDto.class);
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
