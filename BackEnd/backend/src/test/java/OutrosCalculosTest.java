import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

public class OutrosCalculosTest {

    @Test
    void deveRetornarSalarioBrutoZeroQuandoNaoHaAdicionais() {
        Funcionario f = new Funcionario();
        f.setSalarioBase(0.0);
        f.setInsalu(OpcaoAdicional.NAO);
        f.setPericulo(OpcaoAdicional.NAO);

        double resultado = TotalSalarioBruto.calcularSalarioTotalBruto(f);

        assertEquals(0.0, resultado, 0.0001, "Deve retornar 0.0");
    }

    @Test
    void deveDefinirCargaHorariaCorretamente() {
        Funcionario f = new Funcionario();
        f.setCargaHorariaDiaria(9.0);

        assertEquals(9.0, f.getCargaHorariaDiaria(), 0.0001);
    }
}
