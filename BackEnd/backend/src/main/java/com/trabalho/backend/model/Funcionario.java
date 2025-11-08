package com.trabalho.backend.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    private String cargo;
    private Double salarioBase;
    private Double cargaHorariaDiaria;
    private int horasTrabalhadas;
    private int diasTrabalhadasSemana;
    private int diasTrabalhadasMes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataAdmissao;

    private Boolean receberValeTransporte = false;
    private Boolean receberValeAlimentacao = false;
    private Double custoValeTransporte;
    private Double custoDiarioAlimentacao;

    // Usando apenas OpcaoAdicional
    private OpcaoAdicional periculo = OpcaoAdicional.NAO;
    private OpcaoAdicional insalu = OpcaoAdicional.NAO;
    private GrauInsalubridade insalubridade;

    private int dependentes;

    // Getters e Setters
    public Long getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(Long idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(Double salarioBase) { this.salarioBase = salarioBase; }

    public Double getCargaHorariaDiaria() { return cargaHorariaDiaria; }
    public void setCargaHorariaDiaria(Double cargaHorariaDiaria) { this.cargaHorariaDiaria = cargaHorariaDiaria; }

    public int getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(int horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }

    public int getDiasTrabalhadasSemana() { return diasTrabalhadasSemana; }
    public void setDiasTrabalhadasSemana(int diasTrabalhadasSemana) { this.diasTrabalhadasSemana = diasTrabalhadasSemana; }

    public int getDiasTrabalhadasMes() { return diasTrabalhadasMes; }
    public void setDiasTrabalhadasMes(int diasTrabalhadasMes) { this.diasTrabalhadasMes = diasTrabalhadasMes; }

    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }

    public Boolean getReceberValeTransporte() { return receberValeTransporte; }
    public void setReceberValeTransporte(Boolean receberValeTransporte) { this.receberValeTransporte = receberValeTransporte; }

    public Boolean getReceberValeAlimentacao() { return receberValeAlimentacao; }
    public void setReceberValeAlimentacao(Boolean receberValeAlimentacao) { this.receberValeAlimentacao = receberValeAlimentacao; }

    public Double getCustoValeTransporte() { return custoValeTransporte; }
    public void setCustoValeTransporte(Double custoValeTransporte) { this.custoValeTransporte = custoValeTransporte; }

    public Double getCustoDiarioAlimentacao() { return custoDiarioAlimentacao; }
    public void setCustoDiarioAlimentacao(Double custoDiarioAlimentacao) { this.custoDiarioAlimentacao = custoDiarioAlimentacao; }

    public OpcaoAdicional getPericulo() { return periculo; }
    public void setPericulo(OpcaoAdicional periculo) { this.periculo = periculo; }

    public OpcaoAdicional getInsalu() { return insalu; }
    public void setInsalu(OpcaoAdicional insalu) { this.insalu = insalu; }

    public int getDependentes() { return dependentes; }
    public void setDependentes(int dependentes) { this.dependentes = dependentes; }

    public GrauInsalubridade getInsalubridade() {return insalubridade;}
    public void setInsalubridade(GrauInsalubridade insalubridade) {this.insalubridade = insalubridade;}

    
}

