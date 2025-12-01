package com.trabalho.backend.dto;

import java.time.LocalDate;

public class FuncionarioDTO {

    private long idFuncionario;
    private String nome;
    private String cargo;
    private LocalDate dataAdmissao;

    private boolean possuiFolha; // <--- NOVO

    public FuncionarioDTO(){}

    public FuncionarioDTO(Long idFuncionario, String nome, String cargo, LocalDate dataAdmissao, boolean possuiFolha) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.possuiFolha = possuiFolha; // <--- NOVO
    }

    public boolean isPossuiFolha() {
        return possuiFolha;
    }

    public void setPossuiFolha(boolean possuiFolha) {
        this.possuiFolha = possuiFolha;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    
}


