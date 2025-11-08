package com.trabalho.backend.service.calculoAdicionaisService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.GrauInsalubridade;

@Service
public class CalcularInsalubridade implements CalculoAdicionais {

    @Override
    public double calcularAdicional(Funcionario f) {
        if (f.getInsalubridade() == null || f.getSalarioBase() == null) {
            return 0.0;
        }

        switch (f.getInsalubridade()) {
            case ALTO:
                return f.getSalarioBase() * 0.40;
            case MEDIO:
                return f.getSalarioBase() * 0.20;
            case BAIXO:
                return f.getSalarioBase() * 0.10;
            default:
                return 0.0; // sem risco
        }
    }
}
