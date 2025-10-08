import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.GrauInsalubridade;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;

public class CalculoAdicionaisServiceTest {

    @Test
    @DisplayName("Deve calcular corretamente insalubridade e periculosidade em todos os cenários, incluindo casos inválidos")
    void testarCalculoDeAdicionais() {
        Funcionario f = new Funcionario();
        CalcularInsalubridade calcInsalubridade = new CalcularInsalubridade();
        CalcularPericulosidade calcPericulosidade = new CalcularPericulosidade();

        // Caso 1: Insalubridade ALTA e Periculosidade SIM
        f.setSalarioBase(3000.00);
        f.setInsalubridade(GrauInsalubridade.ALTO);
        f.setPericulo(OpcaoAdicional.SIM);

        double adicionalInsalubridade = calcInsalubridade.calcularAdicional(f);
        double adicionalPericulosidade = calcPericulosidade.calcularAdicional(f);

        assertEquals(552.24, adicionalInsalubridade, 0.01, "ALTO deve ser 40% de 1380.60");
        assertEquals(900.00, adicionalPericulosidade, 0.01, "30% do salário base (3000)");

        // Caso 2: Insalubridade MÉDIA e Periculosidade NÃO
        f.setInsalubridade(GrauInsalubridade.MEDIO);
        f.setPericulo(OpcaoAdicional.NAO);

        adicionalInsalubridade = calcInsalubridade.calcularAdicional(f);
        adicionalPericulosidade = calcPericulosidade.calcularAdicional(f);

        assertEquals(276.12, adicionalInsalubridade, 0.01, "MÉDIO deve ser 20% de 1380.60");
        assertEquals(0.0, adicionalPericulosidade, 0.01, "Sem periculosidade retorna 0");

        // Caso 3: Insalubridade BAIXA
        f.setInsalubridade(GrauInsalubridade.BAIXO);
        adicionalInsalubridade = calcInsalubridade.calcularAdicional(f);
        assertEquals(138.06, adicionalInsalubridade, 0.01, "BAIXO deve ser 10% de 1380.60");

        // Caso 4: Nenhuma insalubridade e nenhuma periculosidade
        f.setInsalubridade(null);
        f.setPericulo(OpcaoAdicional.NAO);
        adicionalInsalubridade = calcInsalubridade.calcularAdicional(f);
        adicionalPericulosidade = calcPericulosidade.calcularAdicional(f);

        assertEquals(0.0, adicionalInsalubridade, 0.01, "Sem insalubridade retorna 0");
        assertEquals(0.0, adicionalPericulosidade, 0.01, "Sem periculosidade retorna 0");

        // Caso 5: Salário negativo (inválido)
        f.setSalarioBase(-2000.00);
        f.setInsalubridade(GrauInsalubridade.ALTO);
        f.setPericulo(OpcaoAdicional.SIM);

        adicionalInsalubridade = calcInsalubridade.calcularAdicional(f);
        adicionalPericulosidade = calcPericulosidade.calcularAdicional(f);

        assertEquals(552.24, adicionalInsalubridade, 0.01, "Insalubridade deve ignorar salário negativo");
        assertEquals(-600.00, adicionalPericulosidade, 0.01, "Periculosidade reflete valor negativo (30% de -2000)");

        // Caso 6: Salário nulo (zero)
        f.setSalarioBase(0.0);
        f.setInsalubridade(GrauInsalubridade.ALTO);
        f.setPericulo(OpcaoAdicional.SIM);

        adicionalInsalubridade = calcInsalubridade.calcularAdicional(f);
        adicionalPericulosidade = calcPericulosidade.calcularAdicional(f);

        assertEquals(552.24, adicionalInsalubridade, 0.01, "Insalubridade independe do salário base");
        assertEquals(0.0, adicionalPericulosidade, 0.01, "Periculosidade de salário 0 deve ser 0");
    }
}
