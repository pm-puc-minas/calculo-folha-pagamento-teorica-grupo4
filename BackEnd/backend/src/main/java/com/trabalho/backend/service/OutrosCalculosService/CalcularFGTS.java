package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;

@Service
public class CalcularFGTS implements CalculoAdicionais {

    @Override
    public double calcularAdicional(Funcionario f){
        CalcularInsalubridade insalubridade= new CalcularInsalubridade();
        CalcularPericulosidade periculosidade = new CalcularPericulosidade();

        if (f.getSalarioBase() == null){
            return 0.0;
        }

        //calcular
        if(f.getPericulo() == OpcaoAdicional.SIM && f.getInsalu() == OpcaoAdicional.NAO){
            // Lógica se SÓ tem periculosidade
            return (periculosidade.calcularAdicional(f) + f.getSalarioBase()) * 0.08; 

        } else if (f.getPericulo() == OpcaoAdicional.NAO && f.getInsalu() == OpcaoAdicional.SIM) {
            // Lógica se SÓ tem insalubridade
            return (insalubridade.calcularAdicional(f) + f.getSalarioBase()) * 0.08;
        }

        return f.getSalarioBase() * 0.08;
    }
}
