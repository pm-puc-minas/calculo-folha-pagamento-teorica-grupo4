package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;

public class InssTest {

    @Test
    //metodo para calcular a faixa progressiva

    void testarFaixaProgressiva(){
        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularINSS calcINSS = new CalcularINSS(totalSalarioBruto);
        Funcionario f = new Funcionario();

        // Faixa 1
        f.setSalarioBase(1000.00);
        assertEquals(1000 * 0.075, calcINSS.calcularDesconto(f));

        // Faixa 2
        f.setSalarioBase(2000.00);
        assertEquals(1302*0.075 + (2000-1302)*0.09, calcINSS.calcularDesconto(f));

        // Faixa 3
        f.setSalarioBase(3000.00);
        assertEquals(1302*0.075 + (2571.29-1302)*0.09 + (3000-2571.29)*0.12, calcINSS.calcularDesconto(f));

        // Faixa 4
        f.setSalarioBase(4000.00);
        assertEquals(1302*0.075 + (2571.29-1302)*0.09 + (3856.94-2571.29)*0.12 + (4000-3856.94)*0.14, calcINSS.calcularDesconto(f));

        // Salário no teto
        f.setSalarioBase(7507.49);
        assertEquals(1302*0.075 + (2571.29-1302)*0.09 + (3856.94-2571.29)*0.12 + (7507.49-3856.94)*0.14, calcINSS.calcularDesconto(f));

        // Salário acima do teto
        f.setSalarioBase(8000.00);
        assertEquals(1302*0.075 + (2571.29-1302)*0.09 + (3856.94-2571.29)*0.12 + (7507.49-3856.94)*0.14, calcINSS.calcularDesconto(f));
    }
    
}
