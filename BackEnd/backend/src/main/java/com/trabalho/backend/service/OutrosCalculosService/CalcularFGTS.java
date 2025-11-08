package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.ICalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularFGTS implements ICalculoAdicionais {

    private final TotalSalarioBruto salarioBruto;

    public CalcularFGTS(TotalSalarioBruto salarioBruto){
        this.salarioBruto = salarioBruto;
    }

    @Override
    public double calcularAdicional(Funcionario f){

        // 8% sobre salário bruto
        double fgts = salarioBruto.calcularSalarioTotalBruto(f) * 0.08;

        // Arredondar para 2 casas decimais
        fgts = Math.round(fgts * 100.0) / 100.0;

        return fgts;
    }
}

