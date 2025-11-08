package com.trabalho.backend.service.calculoAdicionaisService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.ICalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularInsalubridade implements ICalculoAdicionais {

    @Override
    public double calcularAdicional(Funcionario f) {
        if (f.getInsalubridade() == null || f.getSalarioBase() == null) {
            return 0.0;
        }

        double adicional;
        switch (f.getInsalubridade()) {
            case ALTO:
                adicional = f.getSalarioBase() * 0.40;
                break;
            case MEDIO:
                adicional = f.getSalarioBase() * 0.20;
                break;
            case BAIXO:
                adicional = f.getSalarioBase() * 0.10;
                break;
            default:
                adicional = 0.0; // sem risco
        }

        // Arredondar para 2 casas decimais
        adicional = Math.round(adicional * 100.0) / 100.0;

        return adicional;
    }
}

