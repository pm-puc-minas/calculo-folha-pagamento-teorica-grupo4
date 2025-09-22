package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularINSS implements CalculoDescontos {

    @Override
    public double calcularDesconto(Funcionario f){
        if(f.getSalarioBase() == null){
            return 0.0;

        } else if(f.getSalarioBase() <= 1580.00){
            return f.getSalarioBase() * 0.075;

        } else if(f.getSalarioBase() <= 2793.88){
            return f.getSalarioBase() * 0.09;

        } else if(f.getSalarioBase() <= 4190.83){
            return f.getSalarioBase() * 0.12;
            
        } else {
            return f.getSalarioBase() * 0.14;
        }
    }

}
