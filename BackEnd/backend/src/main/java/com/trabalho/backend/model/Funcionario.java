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


    private Boolean receberValeTransporte=false;
    private Boolean receberValeAlimentacao=false;
    private Double custoValeTransporte;
    private Double custoDiarioAlimentacao;
    private Boolean periculosidade;
    private GrauInsalubridade insalubridade;
    private OpcaoAdicional Insalu;
    private OpcaoAdicional periculo;
    private int dependentes;

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

    //metodo de insalubridade
    public GrauInsalubridade isInsalubridade(){
        return insalubridade;
    }
    public Double getCustoValeTransporte() {
        return custoValeTransporte;
    }
    public Double getCustoDiarioAlimentacao() {
        return custoDiarioAlimentacao;
    }
    public int getDiasTrabalhadasMes() {
        return diasTrabalhadasMes;
    }
    public OpcaoAdicional getInsalu() {
        return Insalu;
    }
    public OpcaoAdicional getPericulo() {
        return periculo;
    }
    public int getDependentes() {
        return dependentes;
    }
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }
    public void setCargaHorariaDiaria(Double cargaHorariaDiaria) {
        this.cargaHorariaDiaria = cargaHorariaDiaria;
    }
    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }
    public void setDiasTrabalhadasSemana(int diasTrabalhadasSemana) {
        this.diasTrabalhadasSemana = diasTrabalhadasSemana;
    }
    public void setDiasTrabalhadasMes(int diasTrabalhadasMes) {
        this.diasTrabalhadasMes = diasTrabalhadasMes;
    }
    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    public void setReceberValeTransporte(Boolean receberValeTransporte) {
        this.receberValeTransporte = receberValeTransporte;
    }
    public void setReceberValeAlimentacao(Boolean receberValeAlimentacao) {
        this.receberValeAlimentacao = receberValeAlimentacao;
    }
    public void setCustoValeTransporte(Double custoValeTransporte) {
        this.custoValeTransporte = custoValeTransporte;
    }
    public void setCustoDiarioAlimentacao(Double custoDiarioAlimentacao) {
        this.custoDiarioAlimentacao = custoDiarioAlimentacao;
    }
    public void setPericulosidade(Boolean periculosidade) {
        this.periculosidade = periculosidade;
    }
    public void setInsalubridade(GrauInsalubridade insalubridade) {
        this.insalubridade = insalubridade;
    }
    public void setInsalu(OpcaoAdicional insalu) {
        Insalu = insalu;
    }
    public void setPericulo(OpcaoAdicional periculo) {
        this.periculo = periculo;
    }
    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }

}
