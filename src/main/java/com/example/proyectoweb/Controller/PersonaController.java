package com.example.proyectoweb.Controller;

import com.example.proyectoweb.Dto.PersonaDto;
import com.example.proyectoweb.Servicio.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PersonaController {

    private final PersonaService service;

    @GetMapping
    public List<PersonaDto> list() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> get(@PathVariable Long id) {
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonaDto> create(@RequestBody PersonaDto dto) {
        PersonaDto created = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> update(@PathVariable Long id, @RequestBody PersonaDto dto) {
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
