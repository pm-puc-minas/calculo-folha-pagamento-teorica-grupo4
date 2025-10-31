package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.GrauInsalubridade;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;

public class InsalubridadeTest {

    @Test
    void deveRetornarZeroQuandoForNao(){
        // esse método deve rerornar 0 quando o funcionario não tiver insalubridade
        Funcionario f= new Funcionario();
        f.setInsalu(OpcaoAdicional.NAO);
        CalcularInsalubridade insalubridade = new CalcularInsalubridade();

        assertEquals(0.0, insalubridade.calcularAdicional(f));
    }


    @Test
    void deveRetornarOsValoresDeCadaNivel(){

        Funcionario f= new Funcionario();
        f.setInsalu(OpcaoAdicional.SIM);
        f.setInsalubridade(GrauInsalubridade.ALTO);
        CalcularInsalubridade insalubridade = new CalcularInsalubridade();
        //testando nivel alto
        assertEquals(552.24, insalubridade.calcularAdicional(f));

        //testando nivel medio
        f.setInsalubridade(GrauInsalubridade.MEDIO);
        assertEquals(276.12, insalubridade.calcularAdicional(f));

        //testando nivel baixo
        f.setInsalubridade(GrauInsalubridade.BAIXO);
        assertEquals(138.06, insalubridade.calcularAdicional(f));

    }



    
}
