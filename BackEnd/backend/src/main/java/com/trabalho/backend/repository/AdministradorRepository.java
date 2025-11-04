package com.trabalho.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.backend.model.Administrador;

public interface AdministradorRepository extends JpaRepository <Administrador, Long> {
    Optional<Administrador> findByEmail(String email);
    boolean existsByEmail(String email);
}
