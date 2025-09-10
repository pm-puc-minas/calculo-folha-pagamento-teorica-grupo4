package com.trabalho.backend.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;


@Entity
public class Gestao {


    private ArrayList<Funcionario> funcionarios= new ArrayList<>();
    private Administrador administrador;
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    


    
    
}
