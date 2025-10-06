package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;

@Service
public class CalcularSalarioLiquido {

    // Injetando as dependências via construtor (o jeito certo no Spring)
    private final TotalSalarioBruto SalarioBruto;
    private final CalcularINSS calcularINSS;
    private final CalcularIRRF calcularIRRF;
    private final CalcularValeAlime calcularValeAlime;
    private final CalcularValeTrans calcularValeTrans;

    @Autowired
    public CalcularSalarioLiquido(
            TotalSalarioBruto SalarioBruto,
            CalcularINSS calcularINSS,
            CalcularIRRF calcularIRRF,
            CalcularValeAlime calcularValeAlime,
            CalcularValeTrans calcularValeTrans) {
        this.SalarioBruto = SalarioBruto;
        this.calcularINSS = calcularINSS;
        this.calcularIRRF = calcularIRRF;
        this.calcularValeAlime = calcularValeAlime;
        this.calcularValeTrans = calcularValeTrans;
    }

    public double calcularLiquido(Funcionario funcionario) {
        
        // 1. Pega o Salário Bruto
        double salarioBruto = SalarioBruto.calcularSalarioTotalBruto(funcionario);
        
        // 2. Calcula o desconto do INSS (primeiro desconto obrigatório)
        double descontoINSS = calcularINSS.calcularDesconto(funcionario);
        
        // 3. Usa o resultado do INSS para calcular o IRRF
        double descontoIRRF = calcularIRRF.calcularDesconto(funcionario);
        
        // 4. Calcula os outros descontos
        double descontoAlimentacao = calcularValeAlime.calcularDesconto(funcionario);
        double descontoTransporte = calcularValeTrans.calcularDesconto(funcionario);
        
        // 5. Soma todas as deduções
        double totalDeducoes = descontoINSS + descontoIRRF + descontoAlimentacao + descontoTransporte;
        
        // 6. Calcula o valor final
        double salarioLiquido = salarioBruto - totalDeducoes;
        
        return salarioLiquido;
    }
}