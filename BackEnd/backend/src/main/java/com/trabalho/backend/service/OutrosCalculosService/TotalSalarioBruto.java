package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.exception.DadosInvalidosException;
import com.trabalho.backend.exception.ValoresBordasException;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;

@Service
public class TotalSalarioBruto {

    public double calcularSalarioTotalBruto(Funcionario f){

        if (f.getSalarioBase() <= 0) {
            throw new ValoresBordasException("Salário base deve ser maior que zero.");
        }

        // Não permitir os dois ao mesmo tempo
        if (f.getPericulo() == OpcaoAdicional.SIM && f.getInsalu() == OpcaoAdicional.SIM) {
            throw new DadosInvalidosException("Não é permitido aplicar periculosidade e insalubridade ao mesmo tempo.");
        }

        CalcularInsalubridade insalubridade = new CalcularInsalubridade();
        CalcularPericulosidade periculosidade = new CalcularPericulosidade();

        double adicional = 0;

        if (f.getPericulo() == OpcaoAdicional.SIM) {
            adicional = periculosidade.calcularAdicional(f);
        } else if (f.getInsalu() == OpcaoAdicional.SIM) {
            adicional = insalubridade.calcularAdicional(f);
        }


        return f.getSalarioBase() + adicional;
    }
}


