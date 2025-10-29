package com.trabalho.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        FolhaPagamentoRepository folhaRepo
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
    }

    //Gerar folha de pagamento para 1 funcionário
    @Transactional
    public FolhaPagamento gerarFolhaPagamentoPorFuncionario(Funcionario f) {
        funcionarioRepo.save(f); // garante que o funcionário esteja salvo no banco

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

        return folhaRepo.save(folha);
    }

    // Gerar folhas de pagamento para todos os funcionários
    @Transactional
    public List<FolhaPagamento> gerarFolhaParaTodos(List<Funcionario> funcionarios) {
        funcionarioRepo.saveAll(funcionarios);

        List<FolhaPagamento> folhas = funcionarios.stream()
            .map(this::gerarFolhaPagamentoPorFuncionario)
            .collect(Collectors.toList());

        return folhaRepo.saveAll(folhas);
    }

    // Listar todas as folhas
    public List<FolhaPagamento> getTodasFolhas() {
        return folhaRepo.findAll();
    }

    // Buscar folha por funcionário
    public Optional<FolhaPagamento> buscarPeloFuncionario(Funcionario f) {
        return funcionarioRepo.findByCpf(f.getCpf())
                .flatMap(folhaRepo::findByFuncionario);
    }

    // Remover folha de um funcionário
    @Transactional
    public boolean removerFolhaFuncionario(Funcionario f) {
        Optional<FolhaPagamento> folhaOpt = buscarPeloFuncionario(f);
        if (folhaOpt.isPresent()) {
            folhaRepo.delete(folhaOpt.get());
            return true;
        }
        return false;
    }
}




