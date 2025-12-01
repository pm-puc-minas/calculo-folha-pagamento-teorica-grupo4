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
        this.folhaRepo = folhaRepo;
    }

    // Gerar folha para 1 funcionário
    @PostMapping("/gerar/{idFuncionario}")
    public ResponseEntity<FolhaPagamento> gerarFolha(@PathVariable Long idFuncionario) {
        FolhaPagamento folha = folhaService.gerarFolhaPagamentoPorFuncionario(idFuncionario);
        return ResponseEntity.ok(folha);
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

    // Buscar folha pelo ID do funcionário
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<FolhaPagamento> buscarFuncionario(@PathVariable Long idFuncionario) {
        Optional<FolhaPagamento> folha = folhaService.buscarPeloFuncionarioId(idFuncionario);
        return folha.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remover folha pelo ID do funcionário
    @DeleteMapping("/deletarFolha/{idFuncionario}")
    public ResponseEntity<String> removerFolha(@PathVariable Long idFuncionario) {
        boolean removido = folhaService.removerFolhaFuncionario(idFuncionario);
        if (removido) {
            return ResponseEntity.ok("Folha de pagamento removida com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir dados resumidos para o front
    @GetMapping("/mostrar_campo")
    public List<FolhaPagamentoDTO> listarCampos() {
        return folhaRepo.findAll()
            .stream()
            .map(f -> new FolhaPagamentoDTO(
                    f.getFuncionario().getNome(),
                    f.getGeracaoData(),
                    f.getSalarioLiquido()
            ))
            .collect(Collectors.toList());
    }

    @GetMapping("/media")
    public ResponseEntity<Double> mediaSalarioLiquidos() {
        Double media = folhaService.calcularMediaSalarioGeral();
        return ResponseEntity.ok(media);
    }

    @GetMapping("/TotalBruto")
    public ResponseEntity<Double> totalSalarioBruto(){
        Double total= folhaService.somarSalariosBrutos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/TotalLiquido")
    public ResponseEntity<Double> totalSalarioLiquido(){
        Double total= folhaService.somarSalariosLiquidos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/TotalFuncionario")
    public ResponseEntity<Long> totalFuncionario(){
        long total= folhaService.numeroFuncionarios();
        return ResponseEntity.ok(total);
    }


}



