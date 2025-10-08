package com.trabalho.backend.service.calculoDescontosService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@ExtendWith(MockitoExtension.class)
class CalculoDescontosIntegradoTest {

    @Mock
    TotalSalarioBruto totalSalarioBruto;

    @Mock
    Funcionario f;

    @Test
    @DisplayName("Calcula INSS, IRRF, Vale Alimentação e Vale Transporte no mesmo funcionário")
    void deveCalcularDescontosCombinados() {
        // ARRANGE
        when(totalSalarioBruto.calcularSalarioTotalBruto(f)).thenReturn(5000.0);

        when(f.getDependentes()).thenReturn(2);
        when(f.getReceberValeAlimentacao()).thenReturn(true);
        when(f.getCustoDiarioAlimentacao()).thenReturn(20.0);
        when(f.getDiasTrabalhadasMes()).thenReturn(22);
        when(f.getReceberValeTransporte()).thenReturn(true);
        when(f.getCustoValeTransporte()).thenReturn(400.0);

        CalcularINSS inss = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF irrf = new CalcularIRRF(totalSalarioBruto, inss);
        CalcularValeAlime valeAlime = new CalcularValeAlime();
        CalcularValeTrans valeTrans = new CalcularValeTrans(totalSalarioBruto);

        // ACT
        double dInss = inss.calcularDesconto(f);
        double dIrrf = irrf.calcularDesconto(f);
        double dVa   = valeAlime.calcularDesconto(f);
        double dVt   = valeTrans.calcularDesconto(f);
        double total = dInss + dIrrf + dVa + dVt;

        // EXPECTED (pelas faixas do teu código de 2023)
        // INSS(5000) = 526.1925
        // IRRF base = 5000 - INSS - (2*189.59) = 4094.6275
        // IRRF = 22.5% - 651.73 = 269.5611875
        // VA = 22*20 = 440
        // VT = min(6% de 5000 = 300, custo 400) = 300
        // total ≈ 1535.7536875

        assertEquals(526.1925,     dInss,  1e-2, "INSS fora do esperado");
        assertEquals(269.5611875,  dIrrf,  1e-2, "IRRF fora do esperado");
        assertEquals(440.0,        dVa,    1e-6, "VA fora do esperado");
        assertEquals(300.0,        dVt,    1e-6, "VT fora do esperado");
        assertEquals(1535.7536875, total,  1e-2, "Total de descontos fora do esperado");

        verify(totalSalarioBruto, times(4)).calcularSalarioTotalBruto(f);
    }

    @Test
    @DisplayName("Borda: sem VA/VT, 0 dependentes, salário acima do teto do INSS")
    void cenariosDeBorda() {
        // ARRANGE
        when(totalSalarioBruto.calcularSalarioTotalBruto(f)).thenReturn(10000.0); // acima do teto

        when(f.getDependentes()).thenReturn(0);
        when(f.getReceberValeAlimentacao()).thenReturn(false);
        when(f.getCustoDiarioAlimentacao()).thenReturn(0.0);
        when(f.getDiasTrabalhadasMes()).thenReturn(0);
        when(f.getReceberValeTransporte()).thenReturn(false);
        when(f.getCustoValeTransporte()).thenReturn(0.0);

        CalcularINSS inss = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF irrf = new CalcularIRRF(totalSalarioBruto, inss);
        CalcularValeAlime valeAlime = new CalcularValeAlime();
        CalcularValeTrans valeTrans = new CalcularValeTrans(totalSalarioBruto);

        // ACT
        double dInss = inss.calcularDesconto(f); // teto 7507.49
        double dIrrf = irrf.calcularDesconto(f); // 27.5% c/ dedução 884.96
        double dVa   = valeAlime.calcularDesconto(f); // 0 (não recebe)
        double dVt   = valeTrans.calcularDesconto(f); // 0 (não recebe)

        // EXPECTED
        // INSS(teto) ≈ 877.2411
        // IRRF base = 10000 - 877.2411 = 9122.7589
        // IRRF = 0.275 * base - 884.96 ≈ 1623.7986975
        assertEquals(877.2411,     dInss, 1e-2);
        assertEquals(1623.7986975, dIrrf, 1e-2);
        assertEquals(0.0,          dVa,   1e-6);
        assertEquals(0.0,          dVt,   1e-6);

        // VT retorna cedo sem consultar salário
        verify(totalSalarioBruto, times(3)).calcularSalarioTotalBruto(f);
    }
}