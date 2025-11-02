package com.trabalho.backend.service.calculoDescontosService;

import org.springframework.stereotype.Service;

import com.trabalho.backend.model.CalculoDescontos;
import com.trabalho.backend.model.Funcionario;

@Service
public class CalcularValeAlime implements CalculoDescontos{

    @Override
    public double calcularDesconto(Funcionario f){

        //Ponto para inserir tratamento de exceções na sprint 3
        // veriica se o funcionario recebe o vale alimentação
        if(f.getReceberValeAlimentacao()== false){
            return 0.0;
        }

        //evitar valores negativos
        if (f.getCustoDiarioAlimentacao() <= 0 || f.getDiasTrabalhadasMes() <= 0) {
            return 0.0;
        }

        // calcular o desconto
        double valeAlimentacao= f.getDiasTrabalhadasMes() * f.getCustoDiarioAlimentacao();

        return valeAlimentacao;
        


    }
}

