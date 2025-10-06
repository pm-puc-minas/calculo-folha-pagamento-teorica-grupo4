
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;


public class CalculoAdicionaisServiceTest {


    @Test
    void testarPericulosidade() { // não é um método oficial, apenas um exemplo
        Funcionario f= new Funcionario();
        f.setSalarioBase(3000.00); //definir 3 mil reais como valor do salario inicial
        f.setPericulo(OpcaoAdicional.SIM); //definir como sim

        CalcularPericulosidade periculosidade= new CalcularPericulosidade();

        //verificar se vai retornar corretamente
        assertEquals(900.00,periculosidade.calcularAdicional(f) );
    }
    
}
