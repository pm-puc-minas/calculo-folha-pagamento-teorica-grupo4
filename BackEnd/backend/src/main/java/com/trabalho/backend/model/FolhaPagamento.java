package com.trabalho.backend.model;

import jakarta.persistence.Entity;

@Entity
public class FolhaPagamento {

    private Long idFolhaPagamento;
    private Funcionario funcionario;
    private Double salarioBruto;
    private Double salarioLiquido;
    private Double adicionais;
    private Double descontos;
    private Double inss;
    private Double irrf;
    private Double fgts;
    private Double valeTransporte;
    private Double valeAlimentacao;

    //getters
    public Long getIdFolhaPagamento() {
        return idFolhaPagamento;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public Double getSalarioBruto() {
        return salarioBruto;
    }
    public Double getSalarioLiquido() {
        return salarioLiquido;
    }
    public Double getAdicionais() {
        return adicionais;
    }
    public Double getDescontos() {
        return descontos;
    }
    public Double getInss() {
        return inss;
    }
    public Double getIrrf() {
        return irrf;
    }
    public Double getFgts() {
        return fgts;
    }
    public Double getValeTransporte() {
        return valeTransporte;
    }
    public Double getValeAlimentacao() {
        return valeAlimentacao;
    }
}
