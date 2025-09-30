package com.example.proyectoweb;

import com.example.proyectoweb.Dto.ArchDto;
import com.example.proyectoweb.Servicio.ArchService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario sin repos ni mocks. Valida la regla de negocio de self-loop.
 * OJO: usamos nulls en el constructor; este caso no toca repos.
 */
class ArchServiceSelfLoopTest {

    @Test
    void crear_conMismoOrigenYDestino_debeFallar() {
        ArchService service = new ArchService(null, null); // repos no usados en este caso
        ArchDto input = new ArchDto(null, 7L, 7L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.crear(input));
        assertTrue(ex.getMessage().toLowerCase().contains("origen y destino no pueden ser iguales"));
    }
}
