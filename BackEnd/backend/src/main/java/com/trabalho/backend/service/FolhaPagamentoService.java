package com.trabalho.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.trabalho.backend.event.FolhaPagamentoEvent;

import com.trabalho.backend.model.*;
import com.trabalho.backend.repository.FuncionarioRepository;
import com.trabalho.backend.repository.FolhaPagamentoRepository;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;
import com.trabalho.backend.service.OutrosCalculosService.CalcularFGTS;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioHora;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioLiquido;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class FolhaPagamentoService {

    private final CalcularInsalubridade insalubridade;
    private final CalcularPericulosidade periculosidade;
    private final CalcularFGTS fgts;
    private final CalcularSalarioHora salarioHora;
    private final TotalSalarioBruto salarioBruto;
    private final CalcularSalarioLiquido salarioLiquido;
    private final CalcularINSS inss;
    private final CalcularIRRF irrf;
    private final CalcularValeAlime VA;
    private final CalcularValeTrans VT;

    private final FuncionarioRepository funcionarioRepo;
    private final FolhaPagamentoRepository folhaRepo;
    private final ApplicationEventPublisher aviso;

    public FolhaPagamentoService(
        CalcularInsalubridade insalubridade,
        CalcularPericulosidade periculosidade,
        CalcularFGTS fgts,
        CalcularSalarioHora salarioHora,
        TotalSalarioBruto salarioBruto,
        CalcularSalarioLiquido salarioLiquido,
        CalcularINSS inss,
        CalcularIRRF irrf,
        CalcularValeAlime VA,
        CalcularValeTrans VT,
        FuncionarioRepository funcionarioRepo,
        FolhaPagamentoRepository folhaRepo,
        ApplicationEventPublisher aviso
    ) {
        this.insalubridade = insalubridade;
        this.periculosidade = periculosidade;
        this.fgts = fgts;
        this.salarioHora = salarioHora;
        this.salarioBruto = salarioBruto;
        this.salarioLiquido = salarioLiquido;
        this.inss = inss;
        this.irrf = irrf;
        this.VA = VA;
        this.VT = VT;
        this.funcionarioRepo = funcionarioRepo;
        this.folhaRepo = folhaRepo;
        this.aviso=aviso;
    }

    // Gerar folha de pagamento a partir do ID do funcionário
    @Transactional
    public FolhaPagamento gerarFolhaPagamentoPorFuncionario(Long idFuncionario) {
        Funcionario f = funcionarioRepo.findById(idFuncionario)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + idFuncionario));

        FolhaPagamento folha = new FolhaPagamento(f);
        folha.setSalarioBase(f.getSalarioBase());
        folha.setInsalubridade(insalubridade.calcularAdicional(f));
        folha.setPericulosidade(periculosidade.calcularAdicional(f));
        folha.setFgts(fgts.calcularAdicional(f));
        folha.setValorHora(salarioHora.calcularAdicional(f));
        folha.setInss(inss.calcularDesconto(f));
        folha.setIrrf(irrf.calcularDesconto(f));
        folha.setVA(VA.calcularDesconto(f));    
        folha.setVT(VT.calcularDesconto(f));
        folha.setSalarioBruto(salarioBruto.calcularSalarioTotalBruto(f));
        folha.setSalarioLiquido(salarioLiquido.calcularLiquido(f));

        FolhaPagamento folhagerada= folhaRepo.save(folha);
        aviso.publishEvent(new FolhaPagamentoEvent(folhagerada));
        
        return folhagerada;
    }

    // Gerar folhas de pagamento para TODOS os funcionários do banco
    @Transactional
    public List<FolhaPagamento> gerarFolhaParaTodos() {
        List<Funcionario> funcionarios = funcionarioRepo.findAll();

        List<FolhaPagamento> folhas = funcionarios.stream()
            .map(f -> gerarFolhaPagamentoPorFuncionario(f.getIdFuncionario()))
            .collect(Collectors.toList());

        return folhaRepo.saveAll(folhas);
    }

    // Listar todas as folhas de pagamento
    public List<FolhaPagamento> getTodasFolhas() {
        return folhaRepo.findAll();
    }

    // Buscar folha pelo ID do funcionário
    public Optional<FolhaPagamento> buscarPeloFuncionarioId(Long idFuncionario) {
        return funcionarioRepo.findById(idFuncionario)
                .flatMap(folhaRepo::findByFuncionario);
    }

    // Remover folha pelo ID do funcionário
    @Transactional
    public boolean removerFolhaFuncionario(Long idFuncionario) {
        Optional<FolhaPagamento> folhaOpt = buscarPeloFuncionarioId(idFuncionario);
        if (folhaOpt.isPresent()) {
            folhaRepo.delete(folhaOpt.get());
            return true;
        }
        return false;
    }
    // método para calcular a média de salário dos funcionários
    public Double calcularMediaSalarioGeral(){
        List<FolhaPagamento> folhas= folhaRepo.findAll();

        return folhas.stream()
            .mapToDouble(FolhaPagamento::getSalarioLiquido)
            .average()
            .orElse(0.0);
    }
}   






