package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularIRRF implements CalculoDescontos {

    @Override
    public double calcularDesconto(Funcionario f){
        return 2.00; //é um valor fiticio, apenas um exemplo
    }
}
