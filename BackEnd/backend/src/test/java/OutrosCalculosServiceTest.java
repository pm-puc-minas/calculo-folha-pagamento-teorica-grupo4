import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.GrauInsalubridade;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.OutrosCalculosService.CalcularFGTS;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioHora;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioLiquido;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;

public class OutrosCalculosServiceTest {

    @Test
    @DisplayName("Deve testar FGTS, Salário Hora, Salário Líquido e Total Bruto em diferentes cenários")
    void testarOutrosCalculosCompletos() {
        // Configuração básica do funcionário
        Funcionario f = new Funcionario();
        f.setSalarioBase(3000.00);
        f.setPericulo(OpcaoAdicional.SIM);
        f.setInsalu(OpcaoAdicional.NAO);
        f.setInsalubridade(GrauInsalubridade.MEDIO);
        f.setCargaHorariaDiaria(8.0);
        f.setDiasTrabalhadasSemana(5);

        // Dependência principal
        TotalSalarioBruto totalBruto = new TotalSalarioBruto();

        // =====================================================
        // TESTE 1 – Total Salário Bruto
        // =====================================================
        double resultadoBruto = totalBruto.calcularSalarioTotalBruto(f);
        assertEquals(3900.00, resultadoBruto, 0.01, "Salário Bruto = 3000 + 30% de periculosidade");

        // =====================================================
        // TESTE 2 – FGTS (8% do salário bruto)
        // =====================================================
        CalcularFGTS fgts = new CalcularFGTS(totalBruto);
        double valorFGTS = fgts.calcularAdicional(f);
        assertEquals(312.00, valorFGTS, 0.01, "FGTS deve ser 8% do salário bruto (3900)");

        // =====================================================
        // TESTE 3 – Salário Hora
        // =====================================================
        CalcularSalarioHora salarioHora = new CalcularSalarioHora(totalBruto);
        double valorHora = salarioHora.calcularAdicional(f);
        double jornadaMensal = (8 * 5) * 4.5; // 180 horas/mês
        assertEquals(3900.00 / jornadaMensal, valorHora, 0.01, "Valor da hora deve ser salário bruto dividido pela jornada mensal");

        // =====================================================
        // TESTE 4 – Exceções de Carga Horária
        // =====================================================
        Funcionario fErro = new Funcionario();
        fErro.setSalarioBase(2500.00);
        fErro.setCargaHorariaDiaria(9.0);
        fErro.setDiasTrabalhadasSemana(5);

        assertThrows(IllegalArgumentException.class, () -> salarioHora.calcularAdicional(fErro),
                "Deve lançar exceção ao ultrapassar 8 horas diárias");

        fErro.setCargaHorariaDiaria(8.0);
        fErro.setDiasTrabalhadasSemana(6);
        assertThrows(IllegalArgumentException.class, () -> salarioHora.calcularAdicional(fErro),
                "Deve lançar exceção ao ultrapassar 44 horas semanais");

        // =====================================================
        // TESTE 5 – Salário Líquido (mock simplificado)
        // =====================================================
        // Cria versões simplificadas dos descontos (para não depender de regras externas)
        CalcularINSS inss = f1 -> 300.00;
        CalcularIRRF irrf = f1 -> 150.00;
        CalcularValeAlime valeAlime = f1 -> 100.00;
        CalcularValeTrans valeTrans = f1 -> 200.00;

        CalcularSalarioLiquido salarioLiquido = new CalcularSalarioLiquido(
                totalBruto, inss, irrf, valeAlime, valeTrans);

        double liquido = salarioLiquido.calcularLiquido(f);
        double totalDeducoes = 300 + 150 + 100 + 200; // 750 total
        assertEquals(3900.00 - totalDeducoes, liquido, 0.01,
                "Salário líquido = bruto - deduções (INSS, IRRF, VA, VT)");

        // =====================================================
        // TESTE 6 – Sem Adicionais
        // =====================================================
        f.setPericulo(OpcaoAdicional.NAO);
        f.setInsalu(OpcaoAdicional.NAO);
        double resultadoSemAdicional = totalBruto.calcularSalarioTotalBruto(f);
        assertEquals(3000.00, resultadoSemAdicional, 0.01, "Sem adicionais deve retornar apenas o salário base");
    }
}
