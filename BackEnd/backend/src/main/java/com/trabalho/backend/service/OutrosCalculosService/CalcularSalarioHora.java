package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.ICalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularSalarioHora implements ICalculoAdicionais {

    private final TotalSalarioBruto salarioBruto;

    public CalcularSalarioHora(TotalSalarioBruto salarioBruto){
        this.salarioBruto = salarioBruto;
    }

    @Override
    public double calcularAdicional(Funcionario f){

        double jornadaSemanal = f.getCargaHorariaDiaria() * f.getDiasTrabalhadasSemana();

        // Verificações da CLT
        if (f.getCargaHorariaDiaria() > 8 ) {
            throw new IllegalArgumentException("Carga horária diária excedida pela CLT");
        }
        if (jornadaSemanal > 44) {
            throw new IllegalArgumentException("Carga horária semanal excedida pela CLT");
        }

        double jornadaMensal = jornadaSemanal * 4.5;

        // Cálculo do valor da hora
        double salarioHora = salarioBruto.calcularSalarioTotalBruto(f) / jornadaMensal;

        // Arredondar para 2 casas decimais
        salarioHora = Math.round(salarioHora * 100.0) / 100.0;

        return salarioHora;
    }
}
