package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class CalcularINSS implements CalculoDescontos {

    private final TotalSalarioBruto totalSalarioBruto;

    // Construtor para injeção
    public CalcularINSS(TotalSalarioBruto totalSalarioBruto) {
        this.totalSalarioBruto = totalSalarioBruto;
    }

    @Override
    public double calcularDesconto(Funcionario f) {
        double baseDeCalculo = totalSalarioBruto.calcularSalarioTotalBruto(f);

        // TETO DO INSS 2023
        double tetoINSS = 7507.49;
        if (baseDeCalculo > tetoINSS) {
            baseDeCalculo = tetoINSS;
        }

        // Limites de cada faixa
        double limiteFaixa1 = 1302.00;
        double limiteFaixa2 = 2571.29;
        double limiteFaixa3 = 3856.94;

        double descontoTotal = 0.0;

        // Faixa 1
        if (baseDeCalculo > 0) {
            double valorNestaFaixa = Math.min(baseDeCalculo, limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.075;
        }
        // Faixa 2
        if (baseDeCalculo > limiteFaixa1) {
            double valorNestaFaixa = Math.min(baseDeCalculo - limiteFaixa1, limiteFaixa2 - limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.09;
        }
        // Faixa 3
        if (baseDeCalculo > limiteFaixa2) {
            double valorNestaFaixa = Math.min(baseDeCalculo - limiteFaixa2, limiteFaixa3 - limiteFaixa2);
            descontoTotal += valorNestaFaixa * 0.12;
        }
        // Faixa 4
        if (baseDeCalculo > limiteFaixa3) {
            double valorNestaFaixa = baseDeCalculo - limiteFaixa3;
            descontoTotal += valorNestaFaixa * 0.14;
        }

        return Math.round(descontoTotal * 100.0) / 100.0;
    }
}
