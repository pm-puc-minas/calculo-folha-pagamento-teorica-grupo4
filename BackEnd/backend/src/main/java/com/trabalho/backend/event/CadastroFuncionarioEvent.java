package com.trabalho.backend.event;

import com.trabalho.backend.model.Funcionario;
public class CadastroFuncionarioEvent { // essa classe sera uma classe de evento para implementar uns logs de evento
    private final Funcionario funcionario;


    public CadastroFuncionarioEvent(Funcionario funcionario){
        this.funcionario=funcionario;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }

    
}
