package com.trabalho.backend.service.calculoAdicionaisService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularPericulosidade implements CalculoAdicionais {

    @Override
    public double calcularAdicional(Funcionario f){

        if(!f.isPericulosidade()){
            return 0.0;
        }

        return f.getSalarioBase() * 0.30;
    }

}



