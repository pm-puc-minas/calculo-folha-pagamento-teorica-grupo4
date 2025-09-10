package com.trabalho.backend.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.trabalho.backend.model.Funcionario;

@Service
public class GestaoService {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    //metodo para adicionar um funcionario
    public void adicionarFuncionario(Funcionario f){
        funcionarios.add(f);
    }

    //metodo para calcular salario por hora
    public void calcularSalarioHora(){}

    //metodo para calcular periculosidade
    public void calcularPericulosidade(){}


    //metodo para calcular Insalubridade
    public void calcularInsalubridade(){}

    //metodo para calcular vale Transporte
    public void calcularValeTransporte(){}


    //metodo para calcular vale Alimentação
    public void calcularAlimentacao(){}

    //metodo para calcular o desconto do inss
    public void calcularDescontoINSS(){}

    //metodo para calcular FGTS
    public void calcularFGTS(){}

    //metodo para calcular desconto do IRRF
    public void calcularDescontoIRRF(){}

    //metodo para calcular salario liquido
    public void calcularLiquido(){}


    //metodo para gerar um pdf

    //metodo para calcular salario por hora
    public void gerarPDF(FolhaPagamentoService folha){}
    
    
    


    
}
