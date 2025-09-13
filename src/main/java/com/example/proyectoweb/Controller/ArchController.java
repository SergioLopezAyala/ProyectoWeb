package com.example.proyectoweb.Controller;

import com.example.proyectoweb.Dto.ArchDto;
import com.example.proyectoweb.Servicio.ArchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/archs")
public class ArchController {

    private final ArchService service;

    @GetMapping
    public List<ArchDto> list() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<ArchDto> get(@PathVariable Long id) {
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArchDto> create(@RequestBody ArchDto dto) {
        ArchDto created = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchDto> update(@PathVariable Long id, @RequestBody ArchDto dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
