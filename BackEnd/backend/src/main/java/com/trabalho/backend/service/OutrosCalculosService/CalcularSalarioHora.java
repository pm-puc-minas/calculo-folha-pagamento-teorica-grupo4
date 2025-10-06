package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularSalarioHora implements CalculoAdicionais {

    TotalSalarioBruto salarioBruto = new TotalSalarioBruto();
    // atributo global
    private static final double semanaMes= 4.5;

    @Override
    public double calcularAdicional(Funcionario f){
        double jornadaSemanal= f.getCargaHorariaDiaria() *f.getDiasTrabalhadasSemana();

        // verificar a carga a horaria diaria
        if(f.getCargaHorariaDiaria() > 8 ){
            throw new IllegalArgumentException("Carga Horária excedida pelo CLT");
        }
        if(jornadaSemanal>44){
            throw new IllegalArgumentException("Carga Horária semanal excedida pelo CLT");
        }

        double jornadaMensal= jornadaSemanal * semanaMes;

        return salarioBruto.calcularSalarioTotalBruto(f) / jornadaMensal;
    } 

}