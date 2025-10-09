package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.CalcularFGTS;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

public class FgtsTest {

    @Test
    void TestarCorretamente(){
        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularFGTS calcFGTS = new CalcularFGTS(totalSalarioBruto);
        Funcionario f = new Funcionario();

        // 8%
        f.setSalarioBase(2000.0);
        assertEquals(2000.0 * 0.08, calcFGTS.calcularAdicional(f));

    }


    @Test
    void deveRetornarZero(){

        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularFGTS calcFGTS = new CalcularFGTS(totalSalarioBruto);
        Funcionario f = new Funcionario();

        // retorno deve ser 0
        f.setSalarioBase(0.0);
        assertEquals(0.0, calcFGTS.calcularAdicional(f));

    }
    
}
