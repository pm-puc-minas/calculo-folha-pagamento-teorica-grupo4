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

    //metodo para gerar uma folha de Pagamento
    public String gerarFolhaPagamento(Funcionario f){return "Folha Pagamento"; }

    //metodo para gerar um pdf
    public void gerarPDF(){}
    
    
    


    
}
