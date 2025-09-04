package com.example.proyectoweb.Repo;

import com.example.proyectoweb.Modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPersona extends JpaRepository<Persona, Long> {
}
