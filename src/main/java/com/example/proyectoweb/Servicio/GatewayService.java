package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.GatewayDto;
import com.example.proyectoweb.Modelo.Arch;
import com.example.proyectoweb.Modelo.Gateway;
import com.example.proyectoweb.Repo.RepoArch;
import com.example.proyectoweb.Repo.RepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GatewayService {

    private final RepoGateway repo;
    private final RepoArch repoArch; // necesitamos resolver IDs -> Arch

    public GatewayDto crear(GatewayDto dto) {
        Gateway e = new Gateway(null, dto.getType());
        // Si vienen archIds, resolverlos a entidades
        if (dto.getArchIds() != null && !dto.getArchIds().isEmpty()) {
            e.setArchs(resolveArchList(dto.getArchIds()));
        }
        e = repo.save(e);
        return toDto(e);
    }

    public Optional<GatewayDto> obtener(Long id) {
        return repo.findById(id).map(this::toDto);
    }

    public List<GatewayDto> listar() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<GatewayDto> actualizar(Long id, GatewayDto dto) {
        return repo.findById(id).map(existing -> {
            if (dto.getType() != null) {
                existing.setType(dto.getType());
            }
            if (dto.getArchIds() != null) {
                // Si te envían null, no tocamos; si envían lista (incluso vacía), reemplazamos
                existing.setArchs(resolveArchList(dto.getArchIds()));
            }
            Gateway saved = repo.save(existing);
            return toDto(saved);
        });
    }

    public boolean eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    private GatewayDto toDto(Gateway e) {
        List<Long> archIds = (e.getArchs() == null) ? Collections.emptyList()
                : e.getArchs().stream().map(Arch::getId).collect(Collectors.toList());
        return new GatewayDto(e.getId(), e.getType(), archIds);
    }

    private List<Arch> resolveArchList(List<Long> ids) {
        if (ids == null) return Collections.emptyList();
        List<Arch> list = new ArrayList<>(ids.size());
        for (Long id : ids) {
            Arch arch = repoArch.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No existe Arch con id=" + id));
            list.add(arch);
        }
        return list;
    }
}
