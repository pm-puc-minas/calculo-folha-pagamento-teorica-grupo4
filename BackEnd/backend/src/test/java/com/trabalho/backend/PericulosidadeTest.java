package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;

public class PericulosidadeTest {

    @Test
    void DeveRetornarZero(){
        // esse método deve retornar 0 quando não tem periculosidade
        Funcionario f= new Funcionario();
        f.setSalarioBase(1000.00);
        f.setPericulo(OpcaoAdicional.NAO);


        CalcularPericulosidade periculosidade= new CalcularPericulosidade();

        assertEquals(0, periculosidade.calcularAdicional(f));

    }


    @Test
    void deveRetornarCasoForSim(){
        // esse método deve retornar o valor dos 30%
        Funcionario f= new Funcionario();
        f.setSalarioBase(2000.00);
        f.setPericulo(OpcaoAdicional.SIM);

        CalcularPericulosidade periculosidade= new CalcularPericulosidade();

        assertEquals(600.00, periculosidade.calcularAdicional(f));
    }
        


    
}
