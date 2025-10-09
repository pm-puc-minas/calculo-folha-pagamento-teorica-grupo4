package com.trabalho.backend;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TotalSalarioBrutoTest {

    @Test
    void deveRetornarZeroQuandoSalarioBaseForZeroESemAdicionais() {
        Funcionario f = new Funcionario();
        f.setSalarioBase(0.0);
        f.setPericulo(OpcaoAdicional.NAO);
        f.setInsalu(OpcaoAdicional.NAO);

        TotalSalarioBruto total = new TotalSalarioBruto();
        double resultado = total.calcularSalarioTotalBruto(f);
        assertEquals(0.0, resultado, "Deve retornar 0.0 quando o salário base for zero e sem adicionais");
    }
}
