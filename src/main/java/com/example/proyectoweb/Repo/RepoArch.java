package com.example.proyectoweb.Repo;

import com.example.proyectoweb.Modelo.Arch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoArch extends JpaRepository<Arch, Long> {
}
