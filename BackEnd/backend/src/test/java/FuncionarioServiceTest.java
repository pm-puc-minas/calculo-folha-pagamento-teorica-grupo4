import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuncionarioServiceTest {

    @Test
    @DisplayName("Deve validar corretamente os caracteres indesejados no nome do funcionário")
    void deveValidarCaracteresIndesejados() {
        // Arrange (configurar os dados de entrada)
        String nomeInvalido = "João@123";

        // Act (executar o método que será testado)
        boolean contemCaracteresInvalidos = nomeInvalido.matches(".*[^a-zA-ZÀ-ú\\s].*");

        // Assert (verificar se o resultado está correto)
        assertTrue(contemCaracteresInvalidos,
                "O nome contém caracteres indesejados e deve ser identificado como inválido");
    }
}
