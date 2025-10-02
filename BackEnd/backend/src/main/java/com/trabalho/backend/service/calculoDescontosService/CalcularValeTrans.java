package com.trabalho.backend.service.calculoDescontosService;
import com.trabalho.backend.service.calculoAdicionaisService.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularValeTrans implements CalculoDescontos {

    private final CalcularSalarioBruto salarioBruto;

    @Autowired
    public CalcularValeTrans(CalcularSalarioBruto salarioBruto){
        this.salarioBruto= salarioBruto;
    }

    @Override
    public double calcularDesconto(Funcionario f){
        // se o funcionario não usa vale transporte, recebe 0
        if(f.getReceberValeTransporte()== false){
            return 0.0;
        }

        // calcular o teto de 6% sobre o salario bruto
        double teto= this.salarioBruto.calcularSalarioBruto(f)*0.06;

        // guardar o custo real do funcionario em uma variavel
        double custoTotalTransporte= f.getCustoValeTransporte();

        // comparar os dois valores e receber o menor valor
        return Math.min(teto, custoTotalTransporte);

    }
}
