package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularValeAlime implements CalculoAdicionais{

    @Override
    public double calcularAdicional(Funcionario f){
        if(f.getDiasTrabalhadasSemana() <= 0){
            return 0.0;
        } else {
            return f.getDiasTrabalhadasSemana() * 24.00;
        }
    }
}

