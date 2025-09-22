package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularFGTS implements CalculoDescontos {

    @Override
    public double calcularDesconto(Funcionario f){
        if (f.getSalarioBase() == null){
            return 0.0;
        }

        return f.getSalarioBase() * 0.08;

    }
}
