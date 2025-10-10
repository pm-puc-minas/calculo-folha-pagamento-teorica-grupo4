package com.trabalho.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    private Long idAdministrador;
    private String email;
    private String senha;
    

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
