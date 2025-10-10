package com.trabalho.backend.model;


import jakarta.persistence.Entity;

@Entity
public abstract class Usuario {

    private String nome;
    private String cpf;


    // construtor(opcional)


    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    

}
    

    

    
