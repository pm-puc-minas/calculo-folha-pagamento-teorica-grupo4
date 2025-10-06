package com.trabalho.backend.service.calculoDescontosService;
import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class CalcularValeTrans implements CalculoDescontos {

    private final TotalSalarioBruto totalSalarioBruto;

    // Construtor para injeção de dependência
    public CalcularValeTrans(TotalSalarioBruto totalSalarioBruto) {
        this.totalSalarioBruto = totalSalarioBruto;
    }

    @Override
    public double calcularDesconto(Funcionario f) {

        // Se o funcionário não usa vale transporte, recebe 0
        if (!f.getReceberValeTransporte()) {
            return 0.0;
        }

        // Calcular o teto de 6% sobre o salário bruto
        double teto = totalSalarioBruto.calcularSalarioTotalBruto(f) * 0.06;

        // Guardar o custo real do funcionário em uma variável
        double custoTotalTransporte = f.getCustoValeTransporte();

        // Comparar os dois valores e retornar o menor
        return Math.min(teto, custoTotalTransporte);
    }
}
