package com.trabalho.backend.model;


import jakarta.persistence.Entity;

@Entity
public abstract class Usuario {

    private String nome;
    private String email;


    // construtor(opcional)


    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
    

    

    
