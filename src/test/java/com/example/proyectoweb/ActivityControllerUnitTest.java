package com.example.proyectoweb;

import com.example.proyectoweb.Controller.ActivityController;
import com.example.proyectoweb.Dto.ActivityDto;
import com.example.proyectoweb.Servicio.ActividadService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerUnitTest {

    static class ActividadServiceStub extends ActividadService {
        public ActividadServiceStub() { super(null, null, null); }
        @Override public ActivityDto crear(ActivityDto dto) { return new ActivityDto(11L, dto.getName(), dto.getType(), dto.getDescription()); }
        @Override public Optional<ActivityDto> obtener(Long id) { return id==11L ? Optional.of(new ActivityDto(11L,"N","t","d")) : Optional.empty(); }
        @Override public List<ActivityDto> listar() { return List.of(new ActivityDto(11L,"N","t","d")); }
        @Override public Optional<ActivityDto> actualizar(Long id, ActivityDto dto) { return Optional.of(new ActivityDto(id, dto.getName(), dto.getType(), dto.getDescription())); }
        @Override public boolean eliminar(Long id) { return id != 404L; }
    }

    @Test
    void flow_basico() {
        ActivityController c = new ActivityController(new ActividadServiceStub());

        ResponseEntity<ActivityDto> created = c.create(new ActivityDto(null,"A","t","d"));
        assertEquals(201, created.getStatusCode().value());
        assertEquals(11L, created.getBody().getId());

        assertFalse(c.get(404L).getStatusCode().is2xxSuccessful());
        assertTrue(c.delete(11L).getStatusCode().is2xxSuccessful());
        assertEquals(404, c.delete(404L).getStatusCode().value());
    }
}
