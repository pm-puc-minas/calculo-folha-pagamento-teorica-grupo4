package com.trabalho.backend.dto;

import java.time.LocalDate;

public class FolhaPagamentoDTO {

    private Long idFolhaPagamento;
    private String nome;
    private LocalDate dataGeracao;
    private double SalarioLiquido;


    public FolhaPagamentoDTO(String nome, LocalDate dataGeracao, double SalarioLiquido){ // para mostrar no front
        this.nome=nome;
        this.dataGeracao=dataGeracao;
        this.SalarioLiquido= SalarioLiquido;
    }

    public FolhaPagamentoDTO (){} //para jackson



    public LocalDate getDataGeracao() {
        return dataGeracao;
    }


    public double getSalarioLiquido() {
        return SalarioLiquido;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        SalarioLiquido = salarioLiquido;
    }

    public Long getIdFolhaPagamento() {
        return idFolhaPagamento;
    }

    public void setIdFolhaPagamento(Long idFolhaPagamento) {
        this.idFolhaPagamento = idFolhaPagamento;
    }  
}
