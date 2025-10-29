package com.trabalho.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.backend.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
