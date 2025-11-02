package com.trabalho.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trabalho.backend.dto.FolhaPagamentoDTO;
import com.trabalho.backend.model.FolhaPagamento;
import com.trabalho.backend.repository.FolhaPagamentoRepository;
import com.trabalho.backend.service.FolhaPagamentoService;

@RestController
@RequestMapping("/folha-pagamento")
@CrossOrigin(origins = "*")
public class FolhaDePagamentoController {
    
    private final FolhaPagamentoService folhaService;
    private final FolhaPagamentoRepository folhaRepo;

    public FolhaDePagamentoController(FolhaPagamentoService folhaService, FolhaPagamentoRepository folhaRepo) {
        this.folhaService = folhaService;
        this.folhaRepo=folhaRepo;
    }

    //Gerar folha para 1 funcionário
    @PostMapping("/gerar/{idFuncionario}")
    public ResponseEntity<FolhaPagamento> gerarFolha(@PathVariable Long idFuncionario) {
        try {
            FolhaPagamento folha = folhaService.gerarFolhaPagamentoPorFuncionario(idFuncionario);
            return ResponseEntity.ok(folha);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Gerar folhas para todos os funcionários
    @PostMapping("/gerar-todos")
    public ResponseEntity<List<FolhaPagamento>> gerarFolhaParaTodos() {
        List<FolhaPagamento> folhas = folhaService.gerarFolhaParaTodos();
        return ResponseEntity.ok(folhas);
    }

    // Listar todas as folhas
    @GetMapping("/listar")
    public ResponseEntity<List<FolhaPagamento>> listarFolhas() {
        List<FolhaPagamento> folhas = folhaService.getTodasFolhas();
        if (folhas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(folhas);
    }

    // Buscar a folha de pagamento de um funcionário pelo ID
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<FolhaPagamento> buscarFuncionario(@PathVariable Long idFuncionario) {
        Optional<FolhaPagamento> folha = folhaService.buscarPeloFuncionarioId(idFuncionario);
        return folha.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remover a folha de um funcionário pelo ID
    @DeleteMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<String> removerFolha(@PathVariable Long idFuncionario) {
        boolean removido = folhaService.removerFolhaFuncionario(idFuncionario);
        if (removido) {
            return ResponseEntity.ok("Folha de pagamento removida com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //método para mostrar os campos especificos ao gerar a folha de pagamento do funcionario no front
    @GetMapping
    public List<FolhaPagamentoDTO> listarCampos(){
        return folhaRepo.findAll()
            .stream()
            .map(f-> new FolhaPagamentoDTO(f.getFuncionario().getNome(), f.getGeracaoData(), f.getSalarioLiquido()))
            .collect(Collectors.toList());
    }

}


