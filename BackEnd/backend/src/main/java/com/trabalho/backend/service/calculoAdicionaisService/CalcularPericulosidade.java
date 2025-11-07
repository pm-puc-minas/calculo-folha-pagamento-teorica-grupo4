package com.trabalho.backend.service.calculoAdicionaisService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.exception.ValoresBordasException;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;

@Service
public class CalcularPericulosidade implements CalculoAdicionais {

    @Override
    public double calcularAdicional(Funcionario f){

        if (f.getSalarioBase() <= 0) {
            throw new ValoresBordasException("Salário base deve ser maior que zero.");
        }

        // Se não tiver periculosidade, retorna 0
        if (f.getPericulo() == OpcaoAdicional.NAO) {
            return 0.0;
        }

        // Periculosidade = 30% sobre salário base
        return f.getSalarioBase() * 0.30;
    }
}





