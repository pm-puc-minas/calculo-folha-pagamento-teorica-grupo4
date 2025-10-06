import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;

public class OutrosCalculosTest {


    // testar se o salario bruto vai retornar como 0

    @Test
    void verificarSeVaiRetornar0(){
        Funcionario f= new Funcionario();
        //definir os valores como 0
        f.setSalarioBase(0.0);
        f.setInsalu(OpcaoAdicional.NAO);
        f.setPericulo(OpcaoAdicional.NAO);

        TotalSalarioBruto salarioBruto= new TotalSalarioBruto();

        //verificar se vai retonar como 0
        assertEquals(0, salarioBruto.calcularSalarioTotalBruto(f), "deve retonar como 0"); 

    }


    @Test
    void verificarACargaHorariaDiaria(){
        Funcionario f= new Funcionario();

        f.setCargaHorariaDiaria(9.0);

        //veriicar se a carga horaria foi ultrapassada

        assertEquals(9, f.getCargaHorariaDiaria());
    }


    
}
