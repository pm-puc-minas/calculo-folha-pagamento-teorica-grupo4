package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

//Outros nunca é um bom nome para pacotes, corre o risco de colocar qualquer coisa aqui
@Service
public class CalcularFGTS implements CalculoAdicionais {
    private final TotalSalarioBruto salarioBruto;

    public CalcularFGTS(TotalSalarioBruto salarioBruto){
        this.salarioBruto=salarioBruto;
    }

    @Override
    public double calcularAdicional(Funcionario f){

        return salarioBruto.calcularSalarioTotalBruto(f) * 0.08;
    }
}
