package com.example.proyectoweb;

import com.example.proyectoweb.Dto.GatewayDto;
import com.example.proyectoweb.Servicio.GatewayService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario de validación: 'type' es requerido en crear().
 * Sin repos ni mocks, archIds/conditionsJson son null -> no se tocan repos.
 */
class GatewayServiceValidationTest {

    @Test
    void crear_sinTipo_debeFallar() {
        GatewayService service = new GatewayService(null, null); // repos no usados si type==null
        GatewayDto dto = new GatewayDto(null, null, null, null);

        NullPointerException ex = assertThrows(NullPointerException.class, () -> service.crear(dto));
        assertTrue(ex.getMessage().toLowerCase().contains("type requerido"));
    }
}
