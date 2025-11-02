package com.trabalho.backend.dto;

import java.time.LocalDate;

public class FolhaPagamentoDTO {

    private String nome;
    private LocalDate dataGeracao;
    private double SalarioLiquido;


    public FolhaPagamentoDTO(String nome, LocalDate dataGeracao, double salarioLiquido) {
        this.nome = nome;
        this.dataGeracao = dataGeracao;
        SalarioLiquido = salarioLiquido;
    }


    public String getNome() {
        return nome;
    }


    public LocalDate getDataGeracao() {
        return dataGeracao;
    }


    public double getSalarioLiquido() {
        return SalarioLiquido;
    }

    

    


    
}
