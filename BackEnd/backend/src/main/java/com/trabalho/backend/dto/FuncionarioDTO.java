package com.trabalho.backend.dto;

import java.time.LocalDate;

public class FuncionarioDTO {

    private long idFuncionario;
    private String nome;
    private String cargo;
    private LocalDate dataAdmissao;

    public FuncionarioDTO(){}
    

    public FuncionarioDTO(Long idFuncionario, String nome,String cargo, LocalDate dataAdmissao) {
        this.idFuncionario=idFuncionario;
        this.nome = nome;
        this.dataAdmissao = dataAdmissao;
        this.cargo=cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    
    
}

