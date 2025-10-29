package com.trabalho.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trabalho.backend.model.FolhaPagamento;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.FolhaPagamentoService;

@RestController
@RequestMapping("/folha-pagamento")
@CrossOrigin(origins = "*") // permite conectar com o front
public class FolhaDePagamentoController {

    private final FolhaPagamentoService folhaService;

    public FolhaDePagamentoController(FolhaPagamentoService folhaService) {
        this.folhaService = folhaService;
    }

    // 🔹 Gerar folha para 1 funcionário
    @PostMapping("/gerar")
    public ResponseEntity<FolhaPagamento> gerarFolha(@RequestBody Funcionario funcionario) {
        FolhaPagamento folha = folhaService.gerarFolhaPagamentoPorFuncionario(funcionario);
        return ResponseEntity.ok(folha);
    }

    // 🔹 Gerar folhas para todos os funcionários
    @PostMapping("/gerar-folhas")
    public ResponseEntity<List<FolhaPagamento>> gerarFolhaParaTodos(@RequestBody List<Funcionario> funcionarios) {
        List<FolhaPagamento> folhas = folhaService.gerarFolhaParaTodos(funcionarios);
        return ResponseEntity.ok(folhas);
    }

    // 🔹 Listar todas as folhas
    @GetMapping("/listar")
    public ResponseEntity<List<FolhaPagamento>> listarFolhas() {
        List<FolhaPagamento> folhas = folhaService.getTodasFolhas();
        return ResponseEntity.ok(folhas);
    }

    // 🔹 Buscar a folha de pagamento de um funcionário pelo CPF
    @PostMapping("/buscar")
    public ResponseEntity<FolhaPagamento> buscarFuncionario(@RequestBody Funcionario funcionario) {
        Optional<FolhaPagamento> folha = folhaService.buscarPeloFuncionario(funcionario);
        return folha.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Remover a folha de um funcionário pelo CPF
    @PostMapping("/remover")
    public ResponseEntity<String> removerFolha(@RequestBody Funcionario funcionario) {
        boolean removido = folhaService.removerFolhaFuncionario(funcionario);
        if (removido) {
            return ResponseEntity.ok("Folha de pagamento removida com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

