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

    // colocar o principio D
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
        
        // armazenando o salario bruto
        double salarioBruto = SalarioBruto.calcularSalarioTotalBruto(funcionario);
        
        // armazenar o inss
        double descontoINSS = calcularINSS.calcularDesconto(funcionario);
        
        // armazenar o IRRF
        double descontoIRRF = calcularIRRF.calcularDesconto(funcionario);
        
        // armazenar o VA e VT
        double descontoAlimentacao = calcularValeAlime.calcularDesconto(funcionario);
        double descontoTransporte = calcularValeTrans.calcularDesconto(funcionario);
        
        // domar deduções
        double totalDeducoes = descontoINSS + descontoIRRF + descontoAlimentacao + descontoTransporte;
        
        // calcular o salario liquido
        double salarioLiquido = salarioBruto - totalDeducoes;
        
        return salarioLiquido;
    }
}