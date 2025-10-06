package com.trabalho.backend.service.OutrosCalculosService;
import org.springframework.stereotype.Service;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;
import com.trabalho.backend.model.OpcaoAdicional;


@Service
public class TotalSalarioBruto {

    public double calcularSalarioTotalBruto(Funcionario f){
        CalcularInsalubridade insalubridade= new CalcularInsalubridade();
        CalcularPericulosidade periculosidade = new CalcularPericulosidade();

        // Conforme art. 193, §2º da CLT, não é permitido acumular insalubridade e periculosidade.

        if(f.getPericulo() == OpcaoAdicional.SIM && f.getInsalu() == OpcaoAdicional.NAO){
            return f.getSalarioBase() + periculosidade.calcularAdicional(f);
        }

        if(f.getPericulo() == OpcaoAdicional.NAO && f.getInsalu() == OpcaoAdicional.SIM){
            return f.getSalarioBase() + insalubridade.calcularAdicional(f);
        }

        // caso não tenha adicionais, retorna o salario base

        return f.getSalarioBase();
    }
}
