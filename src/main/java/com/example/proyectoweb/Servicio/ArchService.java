package com.example.proyectoweb.Servicio;

import com.example.proyectoweb.Dto.ArchDto;
import com.example.proyectoweb.Modelo.Actividad;
import com.example.proyectoweb.Modelo.Arch;
import com.example.proyectoweb.Repo.RepoActividad;
import com.example.proyectoweb.Repo.RepoArch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchService {

    private final RepoArch repo;
    private final RepoActividad repoActividad;

    public ArchDto crear(ArchDto dto) {

        Actividad actividadI = repoActividad
                .findById(dto.getActividadI())
                .orElseThrow(() -> new IllegalArgumentException("El id de la actividad de inicio no existe"));
        Actividad actividadD = repoActividad
                .findById(dto.getActividadD())
                .orElseThrow(() -> new IllegalArgumentException("El id de la actividad de destino no existe"));

        Arch e = new Arch(null, actividadI, actividadD);
        repo.save(e);

        // Antes: mapper.map(e, ArchDto.class) -> no mapea nested.id
        return new ArchDto(
                e.getActividadI() != null ? e.getActividadI().getId() : null,
                e.getActividadD() != null ? e.getActividadD().getId() : null
        );
    }

    public Optional<ArchDto> obtener(Long id) {
        return repo.findById(id).map(e -> new ArchDto(
                e.getActividadI() != null ? e.getActividadI().getId() : null,
                e.getActividadD() != null ? e.getActividadD().getId() : null
        ));
    }

    public List<ArchDto> listar() {
        List<ArchDto> out = new ArrayList<>();
        for (Arch e : repo.findAll()) {
            out.add(new ArchDto(
                    e.getActividadI() != null ? e.getActividadI().getId() : null,
                    e.getActividadD() != null ? e.getActividadD().getId() : null
            ));
        }
        return out;
    }

    public Optional<ArchDto> actualizar(Long id, ArchDto dto) {
        return repo.findById(id).map(existing -> {
            // Antes: mapper.map(dto, Arch.class) → rompía relaciones
            if (dto.getActividadI() != null) {
                Actividad actividadI = repoActividad
                        .findById(dto.getActividadI())
                        .orElseThrow(() -> new IllegalArgumentException("El id de la actividad de inicio no existe"));
                existing.setActividadI(actividadI);
            }
            if (dto.getActividadD() != null) {
                Actividad actividadD = repoActividad
                        .findById(dto.getActividadD())
                        .orElseThrow(() -> new IllegalArgumentException("El id de la actividad de destino no existe"));
                existing.setActividadD(actividadD);
            }
            Arch saved = repo.save(existing);
            return new ArchDto(
                    saved.getActividadI() != null ? saved.getActividadI().getId() : null,
                    saved.getActividadD() != null ? saved.getActividadD().getId() : null
            );
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
