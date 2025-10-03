package com.trabalho.backend.service.OutrosCalculosService;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.CalculoAdicionais;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularSalarioLiquido implements CalculoAdicionais {

    @Override
    public double calcularAdicional(Funcionario f){
        return 2.00;  //o valor é fiticio, apenas um exemeplo
    }

    
}