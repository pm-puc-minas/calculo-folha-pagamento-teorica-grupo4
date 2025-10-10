package com.trabalho.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    private Long idAdministrador;
    private String senha;
    private String cpf;

    public Long getId() {
        return idAdministrador;
    }

    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
