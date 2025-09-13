package com.trabalho.backend.model;


import jakarta.persistence.Entity;

@Entity
public abstract class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String cpf;


    // construtor(opcional)


    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public String getCpf() {
        return cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
    

    

    
