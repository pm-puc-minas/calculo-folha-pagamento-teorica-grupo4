package com.trabalho.backend.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trabalho.backend.event.FolhaPagamentoEvent;

@Component
public class FolhaListener {

    @EventListener
    public void dispararAviso(FolhaPagamentoEvent aviso){
        System.out.println("Folha gerada para o funcionário: " 
            + aviso.getFolha().getFuncionario().getNome());
    }

    
}
