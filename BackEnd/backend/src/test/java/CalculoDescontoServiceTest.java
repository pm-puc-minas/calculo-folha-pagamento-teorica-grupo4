import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculoDescontoServiceTest {

    @Test
    @DisplayName("Deve lançar exceção ao tentar calcular desconto com valor negativo")
    void deveLancarErroParaValoresNegativos() {
        // Arrange — configurar cenário de teste
        double valorNegativo = -100.0;
        double percentualDesconto = 0.1; // 10%

        CalculoDescontoService service = new CalculoDescontoService();

        // Act + Assert — verificar se lança exceção
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularDesconto(valorNegativo, percentualDesconto),
                "Deve lançar exceção ao receber valores negativos"
        );

        assertEquals("Valor inválido para cálculo de desconto", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve calcular corretamente o valor do desconto")
    void deveCalcularDescontoCorretamente() {
        // Arrange
        double valorOriginal = 1000.0;
        double percentualDesconto = 0.1; // 10%
        CalculoDescontoService service = new CalculoDescontoService();

        // Act
        double resultado = service.calcularDesconto(valorOriginal, percentualDesconto);

        // Assert
        assertEquals(900.0, resultado, 0.0001, "O valor com desconto deve ser 900.0");
    }

    @Test
    @DisplayName("Deve retornar o mesmo valor quando o percentual de desconto for zero")
    void deveRetornarMesmoValorComDescontoZero() {
        // Arrange
        double valorOriginal = 500.0;
        double percentualDesconto = 0.0;
        CalculoDescontoService service = new CalculoDescontoService();

        // Act
        double resultado = service.calcularDesconto(valorOriginal, percentualDesconto);

        // Assert
        assertEquals(500.0, resultado, 0.0001, "Com desconto zero, o valor deve permanecer o mesmo");
    }
}
