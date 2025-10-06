package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class CalcularIRRF implements CalculoDescontos {

    private final TotalSalarioBruto totalSalarioBruto;
    private final CalcularINSS calcularINSS;

    public CalcularIRRF(TotalSalarioBruto totalSalarioBruto, CalcularINSS calcularINSS) {
        this.totalSalarioBruto = totalSalarioBruto;
        this.calcularINSS = calcularINSS;
    }

    @Override
    public double calcularDesconto(Funcionario f) {

        double salarioBruto = totalSalarioBruto.calcularSalarioTotalBruto(f);
        double descontoINSS = calcularINSS.calcularDesconto(f);

        // Dedução por dependente
        double deducaoDependente = 189.59;
        double totalDeducaoDependentes = f.getDependentes() * deducaoDependente;

        // Base de cálculo do IRRF
        double baseCalculo = salarioBruto - descontoINSS - totalDeducaoDependentes;

        double aliquota = 0.0;
        double deducao = 0.0;

        // Aplicar tabela progressiva
        if (baseCalculo <= 1903.98) {
            aliquota = 0.0;
            deducao = 0.0;
        } else if (baseCalculo <= 2826.65) {
            aliquota = 0.075;
            deducao = 158.40;
        } else if (baseCalculo <= 3751.05) {
            aliquota = 0.15;
            deducao = 370.40;
        } else if (baseCalculo <= 4664.68) {
            aliquota = 0.225;
            deducao = 651.73;
        } else {
            aliquota = 0.275;
            deducao = 884.96;
        }

        // Cálculo do IRRF
        double irrf = (baseCalculo * aliquota) - deducao;

        // Se negativo, zera (isento)
        if (irrf < 0) {
            irrf = 0.0;
        }

        // Arredondar para 2 casas decimais
        return Math.round(irrf * 100.0) / 100.0;
    }
}

