package com.trabalho.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trabalho.backend.model.FolhaPagamento;
import com.trabalho.backend.model.Funcionario;
import java.util.Optional;

public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long> {
    Optional<FolhaPagamento> findByFuncionario(Funcionario funcionario);
}

