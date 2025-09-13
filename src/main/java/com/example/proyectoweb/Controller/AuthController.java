package com.example.proyectoweb.Controller;

import com.example.proyectoweb.Dto.PersonaDto;
import com.example.proyectoweb.Servicio.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final PersonaService personas;

    public record LoginRequest(String email, String password) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return personas.obtenerPorEmail(req.email())
                .filter(p -> p.getPassword() != null && p.getPassword().equals(req.password()))
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas"));
    }
}
