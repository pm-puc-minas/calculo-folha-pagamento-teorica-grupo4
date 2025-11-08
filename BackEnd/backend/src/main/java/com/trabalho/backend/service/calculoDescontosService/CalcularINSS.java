package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.ICalculoDescontos;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class CalcularINSS implements ICalculoDescontos {

    private final TotalSalarioBruto SalarioBruto;

    public CalcularINSS(TotalSalarioBruto SalarioBruto) {
        this.SalarioBruto = SalarioBruto;
    }

    @Override
    public double calcularDesconto(Funcionario f) {
        double salarioInicial = SalarioBruto.calcularSalarioTotalBruto(f);

        // Teto do INSS 2023
        double tetoINSS = 7507.49;
        if (salarioInicial > tetoINSS) {
            salarioInicial = tetoINSS;
        }

        // Faixas do INSS
        double limiteFaixa1 = 1302.00;
        double limiteFaixa2 = 2571.29;
        double limiteFaixa3 = 3856.94;

        double descontoTotal = 0.0;

        if (salarioInicial > 0) {
            double valorNestaFaixa = Math.min(salarioInicial, limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.075;
        }
        if (salarioInicial > limiteFaixa1) {
            double valorNestaFaixa = Math.min(salarioInicial - limiteFaixa1, limiteFaixa2 - limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.09;
        }
        if (salarioInicial > limiteFaixa2) {
            double valorNestaFaixa = Math.min(salarioInicial - limiteFaixa2, limiteFaixa3 - limiteFaixa2);
            descontoTotal += valorNestaFaixa * 0.12;
        }
        if (salarioInicial > limiteFaixa3) {
            double valorNestaFaixa = salarioInicial - limiteFaixa3;
            descontoTotal += valorNestaFaixa * 0.14;
        }

        // Arredondar para 2 casas decimais
        descontoTotal = Math.round(descontoTotal * 100.0) / 100.0;

        return descontoTotal;
    }
}

