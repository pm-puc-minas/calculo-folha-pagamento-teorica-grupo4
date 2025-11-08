package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.ICalculoDescontos;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class CalcularValeTrans implements ICalculoDescontos {

    private final TotalSalarioBruto totalSalarioBruto;

    public CalcularValeTrans(TotalSalarioBruto totalSalarioBruto) {
        this.totalSalarioBruto = totalSalarioBruto;
    }

    @Override
    public double calcularDesconto(Funcionario f) {

        // Se o funcionário não usa vale transporte, retorna 0
        if (!f.getReceberValeTransporte()) {
            return 0.0;
        }

        // Calcular o teto de 6% sobre o salário bruto
        double teto = totalSalarioBruto.calcularSalarioTotalBruto(f) * 0.06;

        // Guardar o custo real do funcionário
        double custoTotalTransporte = f.getCustoValeTransporte();

        // Escolher o menor valor entre o teto e o custo real
        double VT = Math.min(teto, custoTotalTransporte);

        // Arredondar para 2 casas decimais
        VT = Math.round(VT * 100.0) / 100.0;

        return VT;
    }
}
