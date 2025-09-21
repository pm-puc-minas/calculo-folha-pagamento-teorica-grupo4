package com.trabalho.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;


@Entity
public class Funcionario extends Usuario {

    private Long idFuncionario;
    private String cargo;
    private Double salarioBase;
    private Double cargaHorariaDiaria;
    private int horasTrabalhadas;
    private int diasTrabalhadasSemana;
    private LocalDate dataAdmissao;
    private Boolean receberValeTransporte;
    private Boolean receberValeAlimentacao;
    private Boolean periculosidade;
    private GrauInsalubridade insalubridade;

    //getters
    public Long getIdFuncionario() {
        return idFuncionario;
    }
    public String getCargo() {
        return cargo;
    }
    public Double getSalarioBase() {
        return salarioBase;
    }
    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }
    public int getDiasTrabalhadasSemana() {
        return diasTrabalhadasSemana;
    }
    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }
    public Boolean getReceberValeTransporte() {
        return receberValeTransporte;
    }
    public Boolean getReceberValeAlimentacao() {
        return receberValeAlimentacao;
    }
    public Boolean getPericulosidade() {
        return periculosidade;
    }
    public GrauInsalubridade getInsalubridade() {
        return insalubridade;
    }
    public Double getCargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    // metodo de periculosidade
    public boolean isPericulosidade(){
        return periculosidade;
    }

    
    
}
