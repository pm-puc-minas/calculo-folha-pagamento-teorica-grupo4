package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularINSS implements CalculoDescontos {

    @Override
    public double calcularDesconto(Funcionario f) {
        // Usaremos uma variável para a base de cálculo para facilitar
        Double baseDeCalculo = f.getSalarioBase(); 

        if (baseDeCalculo == null) {
            return 0.0;
        }

        // TETO DO INSS 2023: Ninguém pode contribuir sobre um valor maior que este
        double tetoINSS = 7507.49;
        if (baseDeCalculo > tetoINSS) {
            baseDeCalculo = tetoINSS;
        }

        // Limites de cada faixa da tabela de 2023
        double limiteFaixa1 = 1302.00;
        double limiteFaixa2 = 2571.29;
        double limiteFaixa3 = 3856.94;

        double descontoTotal = 0.0;

        // --- CÁLCULO FAIXA A FAIXA ---

        // Faixa 1 (7.5%)
        if (baseDeCalculo > 0) {
            double valorNestaFaixa = Math.min(baseDeCalculo, limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.075;
        }

        // Faixa 2 (9%)
        if (baseDeCalculo > limiteFaixa1) {
            double valorNestaFaixa = Math.min(baseDeCalculo - limiteFaixa1, limiteFaixa2 - limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.09;
        }

        // Faixa 3 (12%)
        if (baseDeCalculo > limiteFaixa2) {
            double valorNestaFaixa = Math.min(baseDeCalculo - limiteFaixa2, limiteFaixa3 - limiteFaixa2);
            descontoTotal += valorNestaFaixa * 0.12;
        }

        // Faixa 4 (14%)
        if (baseDeCalculo > limiteFaixa3) {
            double valorNestaFaixa = baseDeCalculo - limiteFaixa3;
            descontoTotal += valorNestaFaixa * 0.14;
        }

        // Arredondando para 2 casas decimais para evitar problemas com double
        return Math.round(descontoTotal * 100.0) / 100.0;
    }

}
