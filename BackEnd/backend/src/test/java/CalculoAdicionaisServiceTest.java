import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;

public class CalculoAdicionaisServiceTest {

    @Test
    @DisplayName("Deve calcular corretamente o adicional de periculosidade quando ativado")
    void deveCalcularPericulosidadeCorretamente() {
        // Arrange – configurar o cenário de teste
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBase(3000.00);
        funcionario.setPericulo(OpcaoAdicional.SIM);

        CalcularPericulosidade calcularPericulosidade = new CalcularPericulosidade();

        // Act – executar o cálculo
        double resultado = calcularPericulosidade.calcularAdicional(funcionario);

        // Assert – verificar se o resultado está correto (30% de 3000 = 900)
        assertEquals(900.00, resultado, 0.0001,
                "O adicional de periculosidade deve ser 30% do salário base");
    }

    @Test
    @DisplayName("Não deve aplicar adicional de periculosidade quando não selecionado")
    void naoDeveAplicarPericulosidadeQuandoNaoSelecionado() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBase(3000.00);
        funcionario.setPericulo(OpcaoAdicional.NAO);

        CalcularPericulosidade calcularPericulosidade = new CalcularPericulosidade();

        // Act
        double resultado = calcularPericulosidade.calcularAdicional(funcionario);

        // Assert
        assertEquals(0.0, resultado, 0.0001,
                "Quando periculosidade for 'NAO', o adicional deve ser zero");
    }

    @Test
    @DisplayName("Deve retornar zero se o salário base for zero")
    void deveRetornarZeroSeSalarioBaseForZero() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBase(0.0);
        funcionario.setPericulo(OpcaoAdicional.SIM);

        CalcularPericulosidade calcularPericulosidade = new CalcularPericulosidade();

        // Act
        double resultado = calcularPericulosidade.calcularAdicional(funcionario);

        // Assert
        assertEquals(0.0, resultado, 0.0001,
                "Mesmo com periculosidade 'SIM', salário base zero deve resultar em adicional zero");
    }
}
