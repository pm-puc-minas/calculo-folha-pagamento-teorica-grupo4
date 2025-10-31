package com.trabalho.backend.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trabalho.backend.event.CadastroFuncionarioEvent;


@Component
public class FuncionarioListener {
    @EventListener
    public void avisoFuncionarioCadastrado(CadastroFuncionarioEvent aviso){
        System.out.println("Funcionário Cadastrado: " +aviso.getFuncionario().getNome());
    } 
}
