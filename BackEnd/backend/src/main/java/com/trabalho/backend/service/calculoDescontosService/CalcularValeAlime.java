package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.exception.ValoresBordasException;
import com.trabalho.backend.model.ICalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularValeAlime implements ICalculoDescontos {

    @Override
    public double calcularDesconto(Funcionario f) {

        // Verifica se o funcionário recebe o vale alimentação
        if (f.getReceberValeAlimentacao() == false) {
            return 0.0;
        }

        // Evitar valores negativos
        if (f.getCustoDiarioAlimentacao() <= 0 || f.getDiasTrabalhadasMes() <= 0) {
            throw new ValoresBordasException("O valor nao pode ser negativo.");
        }

        double valorTotal = f.getDiasTrabalhadasMes() * f.getCustoDiarioAlimentacao();
        double descontoFuncionario = valorTotal * 0.20; // 20% do custo
        return Math.round(descontoFuncionario * 100.0) / 100.0;
    }
}


