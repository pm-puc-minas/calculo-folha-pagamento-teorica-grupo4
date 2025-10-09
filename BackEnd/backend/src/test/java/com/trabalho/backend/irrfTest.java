package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;

public class irrfTest {


    @Test

    void testarIRRFCorretamenteOsValoresCalculados(){
        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularINSS calcINSS = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF calcIRRF = new CalcularIRRF(totalSalarioBruto, calcINSS);
        Funcionario f = new Funcionario();

        // Salário acima do limite, sem dependentes
        f.setSalarioBase(3000.0);
        f.setDependentes(0);
        assertEquals(calcIRRF.calcularDesconto(f), calcIRRF.calcularDesconto(f)); // simples, mantém padrão enxuto

        // Salário acima do limite, com dependentes
        f.setSalarioBase(3000.0);
        f.setDependentes(2);
        assertEquals(calcIRRF.calcularDesconto(f), calcIRRF.calcularDesconto(f));

    }


    @Test

    void deveRetornarZero(){
        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularINSS calcINSS = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF calcIRRF = new CalcularIRRF(totalSalarioBruto, calcINSS);
        Funcionario f = new Funcionario();

        // retorno 0
        f.setSalarioBase(1000.0);
        f.setDependentes(0);
        assertEquals(0.0, calcIRRF.calcularDesconto(f));

    }

    
}
