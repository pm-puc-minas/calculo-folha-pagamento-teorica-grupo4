package com.trabalho.backend.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FolhaPagamento { // essa classe vai ser como um recibo para cada funcionario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFolhaPagamento;

    @OneToOne  // cada funcionario só tem uma folha de pagamento
    @JoinColumn(name= "funcionario_id")
    private Funcionario funcionario;

    private double salarioBase;
    private double salarioBruto;
    private double salarioLiquido;

    private double insalubridade;
    private double periculosidade;
    private double fgts;
    private double valorHora;

    private double inss;
    private double irrf;
    private double VA;
    private double VT;
    
    private LocalDate geracaoData;
    
    public FolhaPagamento(){
    }


    public FolhaPagamento(Funcionario funcionario){
        this.funcionario= funcionario;
        this.geracaoData= LocalDate.now();
    }


    public long getIdFolhaPagamento() {return idFolhaPagamento;}
    public void setIdFolhaPagamento(long idFolhaPagamento) {this.idFolhaPagamento = idFolhaPagamento;}
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    public double getSalarioBruto() { return salarioBruto; }
    public void setSalarioBruto(double salarioBruto) { this.salarioBruto = salarioBruto; }
    public double getSalarioLiquido() { return salarioLiquido; }
    public void setSalarioLiquido(double salarioLiquido) { this.salarioLiquido = salarioLiquido; }
    public double getInsalubridade() { return insalubridade; }
    public void setInsalubridade(double insalubridade) { this.insalubridade = insalubridade; }
    public double getPericulosidade() { return periculosidade; }
    public void setPericulosidade(double periculosidade) { this.periculosidade = periculosidade; }
    public double getFgts() { return fgts; }
    public void setFgts(double fgts) { this.fgts = fgts; }
    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }
    public double getInss() { return inss; }
    public void setInss(double inss) { this.inss = inss; }
    public double getIrrf() { return irrf; }
    public void setIrrf(double irrf) { this.irrf = irrf; }
    public double getVA() { return VA; }
    public void setVA(double vA) { VA = vA; }
    public double getVT() { return VT; }
    public void setVT(double vT) { VT = vT; }
    public LocalDate getGeracaoData() { return geracaoData; }
    public void setGeracaoData(LocalDate geracaoData) { this.geracaoData = geracaoData; }
    
}
