package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.exception.DadosInvalidosException;
import com.trabalho.backend.exception.ValoresBordasException;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;
import com.trabalho.backend.model.OpcaoAdicional;

@Service
public class TotalSalarioBruto {

    public double calcularSalarioTotalBruto(Funcionario f){

        if (f.getSalarioBase() <= 0) {
            throw new ValoresBordasException("Salário base deve ser maior que zero.");
        }

        if (f.getPericulo() == OpcaoAdicional.SIM && f.getInsalu() == OpcaoAdicional.SIM) {
            throw new DadosInvalidosException("Não é permitido aplicar periculosidade e insalubridade ao mesmo tempo.");
        }

        CalcularInsalubridade insalubridade = new CalcularInsalubridade();
        CalcularPericulosidade periculosidade = new CalcularPericulosidade();

        // Se tiver apenas periculosidade
        if (f.getPericulo() == OpcaoAdicional.SIM && f.getInsalu() == OpcaoAdicional.NAO) {
            return f.getSalarioBase() + periculosidade.calcularAdicional(f);
        }

        // Se tiver apenas insalubridade
        if (f.getPericulo() == OpcaoAdicional.NAO && f.getInsalu() == OpcaoAdicional.SIM) {
            return f.getSalarioBase() + insalubridade.calcularAdicional(f);
        }

        // Sem adicionais
        return f.getSalarioBase();
    }
}

