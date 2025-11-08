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

        // Calcular o desconto
        double valeAlimentacao = f.getDiasTrabalhadasMes() * f.getCustoDiarioAlimentacao();

        // Arredondar para 2 casas decimais
        valeAlimentacao = Math.round(valeAlimentacao * 100.0) / 100.0;

        return valeAlimentacao;
    }
}


