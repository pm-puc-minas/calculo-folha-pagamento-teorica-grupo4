package com.trabalho.backend.service.calculoAdicionaisService;
import com.trabalho.backend.model.Funcionario;

public class CalcularSalarioBruto {

    // calcular o salario Bruto

    public double calcularSalarioBruto(Funcionario func){

        CalcularInsalubridade insalubridade = new CalcularInsalubridade();
        CalcularPericulosidade periculosidade = new CalcularPericulosidade();

        // pegar os valores adicionais e guardar em uma varíaveis para a melhor flexiblidade
        double valorDoAdicionalDeInsalubridade = insalubridade.calcularAdicional(func);
        double valorDoAdicionalDePericulosidade = periculosidade.calcularAdicional(func);
        //Calcular qual tem o maior valor, pois segundo o CLT... não permite que o trabalhador tenha dois adicionais ao mesmo tempo
        double adicionalLegalASerPago = Math.max(valorDoAdicionalDeInsalubridade, valorDoAdicionalDePericulosidade);

        // Agora some o salário base com o adicional CORRETO (o maior dos dois).
        double salario_bruto = func.getSalarioBase() + adicionalLegalASerPago;

        return salario_bruto;
    }
}
