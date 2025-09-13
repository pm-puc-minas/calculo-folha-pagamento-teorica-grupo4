package com.trabalho.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Administrador {

    private Long idAdministrador;

    public Long getId() {
        return idAdministrador;
    }
    
}
