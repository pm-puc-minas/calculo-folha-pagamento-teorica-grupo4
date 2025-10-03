package com.trabalho.backend.service.calculoAdicionaisService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.GrauInsalubridade;

@Service
public class CalcularInsalubridade implements CalculoAdicionais {

    private static final double SALARIO_MINIMO = 1380.60;


    @Override
    public double calcularAdicional(Funcionario f){
        if(f.getInsalubridade() == GrauInsalubridade.ALTO){
            return SALARIO_MINIMO * 0.40;
        } else if(f.getInsalubridade() == GrauInsalubridade.MEDIO){
            return SALARIO_MINIMO * 0.20;
        } else if(f.getInsalubridade() == GrauInsalubridade.BAIXO){
            return SALARIO_MINIMO * 0.10;
        } else {
            return 0.0; // se não tiver risco, retorna sem adicional
        }
    }

    
}