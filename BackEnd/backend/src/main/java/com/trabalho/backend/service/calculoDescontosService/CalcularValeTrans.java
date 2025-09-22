package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularValeTrans implements CalculoDescontos {

    @Override
    public double calcularDesconto(Funcionario f){

        if (f.getSalarioBase() == null){
            return 0.0;
        }

        return f.getsalarioBase9() * 0.06;
    }
}
