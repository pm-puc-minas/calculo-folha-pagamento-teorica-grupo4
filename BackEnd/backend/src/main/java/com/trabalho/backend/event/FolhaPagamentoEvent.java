package com.trabalho.backend.event;
import com.trabalho.backend.model.FolhaPagamento;

public class FolhaPagamentoEvent {

    private final FolhaPagamento folha;

    public FolhaPagamentoEvent(FolhaPagamento folha) {
        this.folha = folha;
    }

    public FolhaPagamento getFolha() {
        return folha;
    }

}
