package com.trabalho.backend.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.trabalho.backend.model.*;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioLiquido;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioHora;
import com.trabalho.backend.service.OutrosCalculosService.CalcularFGTS;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;


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

    private final Map<Funcionario, FolhaPagamento> folhas= new HashMap<>();

    public FolhaPagamentoService(CalcularInsalubridade insalubridade, CalcularPericulosidade periculosidade,
            CalcularFGTS fgts, CalcularSalarioHora salarioHora, TotalSalarioBruto salarioBruto,
            CalcularSalarioLiquido salarioLiquido, CalcularINSS inss, CalcularIRRF irrf, CalcularValeAlime vA,
            CalcularValeTrans vT) {
        this.insalubridade = insalubridade;
        this.periculosidade = periculosidade;
        this.fgts = fgts;
        this.salarioHora = salarioHora;
        this.salarioBruto = salarioBruto;
        this.salarioLiquido = salarioLiquido;
        this.inss = inss;
        this.irrf = irrf;
        VA = vA;
        VT = vT;
    }


    // esse método gera a folha de pagamento de um funcionário por vez
    public FolhaPagamento gerarFolhaPagamentoPorFuncionario(Funcionario f){
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
        
        //adicionar na lista de folhas
        folhas.put(f, folha);
        return folha;
    }


    // esse método gera a folha de pagamento para todos funcionários em uma única lista
    public List<FolhaPagamento> gerarFolhaParaTodos(List<Funcionario> funcionarios){
        return funcionarios.stream()
            .map(this::gerarFolhaPagamentoPorFuncionario)
            .collect(Collectors.toList());

    }


    // método para retornar todas folhas que já foram geradas
    public List<FolhaPagamento> getTodasFolhas(){
        return new ArrayList<>(folhas.values());
    }


    // método para procuar a folha de um funcionário através do objeto Funcionario
    public Optional<FolhaPagamento> buscarPeloFuncionario(Funcionario f){
        return Optional.ofNullable(folhas.get(f));
    
    }

    //método para remover a folha de funcionário
    public boolean removerFolhaFuncionario(Funcionario f) {
        return folhas.remove(f) != null;
    }

}



