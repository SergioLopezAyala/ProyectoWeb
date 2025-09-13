package com.example.proyectoweb.Controller;

import com.example.proyectoweb.Dto.ProcesoDto;
import com.example.proyectoweb.Servicio.ProcesoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/processes")
public class ProcesoController {

    private final ProcesoService service;

    @GetMapping
    public List<ProcesoDto> list() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesoDto> get(@PathVariable Long id) {
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProcesoDto> create(@RequestBody ProcesoDto dto) {
        ProcesoDto created = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcesoDto> update(@PathVariable Long id, @RequestBody ProcesoDto dto) {
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
