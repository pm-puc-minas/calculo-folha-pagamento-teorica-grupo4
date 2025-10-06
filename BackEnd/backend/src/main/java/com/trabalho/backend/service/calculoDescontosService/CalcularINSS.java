package com.trabalho.backend.service.calculoDescontosService;
import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

@Service
public class CalcularINSS implements CalculoDescontos {

    private final TotalSalarioBruto SalarioBruto;

    // Para a maioria dos calculos foi adicionado o principio SOLID como D nesse caso
    public CalcularINSS(TotalSalarioBruto SalarioBruto) {
        this.SalarioBruto = SalarioBruto;
    }

    @Override
    public double calcularDesconto(Funcionario f) {
        double salarioInicial = SalarioBruto.calcularSalarioTotalBruto(f);

        // pegando o teto do ano de 2023 
        double tetoINSS = 7507.49;
        if (salarioInicial > tetoINSS) {
            salarioInicial = tetoINSS;
        }

        // numerando faixas
        double limiteFaixa1 = 1302.00;
        double limiteFaixa2 = 2571.29;
        double limiteFaixa3 = 3856.94;

        double descontoTotal = 0.0;

        
        if (salarioInicial > 0) {
            double valorNestaFaixa = Math.min(salarioInicial, limiteFaixa1); // esse math min retorna o menor numero entre dois elementos
            descontoTotal += valorNestaFaixa * 0.075; // o menor numero extraido é adicionado nessa varíavel
        }
        
        if (salarioInicial > limiteFaixa1) {
            double valorNestaFaixa = Math.min(salarioInicial - limiteFaixa1, limiteFaixa2 - limiteFaixa1);
            descontoTotal += valorNestaFaixa * 0.09;
        }
        // Faixa 3
        if (salarioInicial > limiteFaixa2) {
            double valorNestaFaixa = Math.min(salarioInicial - limiteFaixa2, limiteFaixa3 - limiteFaixa2);
            descontoTotal += valorNestaFaixa * 0.12;
        }
        // Faixa 4
        if (salarioInicial > limiteFaixa3) {
            double valorNestaFaixa = salarioInicial - limiteFaixa3;
            descontoTotal += valorNestaFaixa * 0.14;
        }

        return (descontoTotal * 100.0) / 100.0;  // não consegui pensar outros métodos para chegar num resultado com mais precisão
    }
}
