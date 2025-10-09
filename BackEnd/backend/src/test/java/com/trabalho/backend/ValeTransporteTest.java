package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;

public class ValeTransporteTest {


    @Test
    void deveRetornarZeroQuandoNaoTemVT(){
        // metodo pra validar quandoo o funcionario nao tiver um vale transporte
        Funcionario f= new Funcionario();
        f.setReceberValeTransporte(false);
        f.setSalarioBase(2000.00);

        TotalSalarioBruto salarioBruto= new TotalSalarioBruto();
        CalcularValeTrans transporte= new CalcularValeTrans(salarioBruto);

        assertEquals(0.0, transporte.calcularDesconto(f));
    }

    @Test
    void deveRetornarOValorQuandoForSim(){
        // metodo pra validar quandoo o funcionario tiver um vale transporte e retornar o valor
        Funcionario f= new Funcionario();
        f.setReceberValeTransporte(true);
        f.setSalarioBase(2000.00);
        f.setCustoValeTransporte(80.00); // isso tem o custo menor que teto

        TotalSalarioBruto salarioBruto= new TotalSalarioBruto();
        CalcularValeTrans transporte= new CalcularValeTrans(salarioBruto);

        assertEquals(80.00, transporte.calcularDesconto(f));

        //agora o custo maior que o teto

        f.setCustoValeTransporte(200.00); // isso tem o custo maior que teto
        assertEquals(120.00, transporte.calcularDesconto(f));

    }

}
