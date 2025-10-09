package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;


public class ValeAlimentacaoTest {


    @Test
    void deveRetornarZeroQuandoNaoTemVA(){
        // metodo pra validar quandoo o funcionario nao tiver um vale alimentacao
        Funcionario f= new Funcionario();
        f.setReceberValeAlimentacao(false);
        f.setDiasTrabalhadasMes(20);
        f.setCustoDiarioAlimentacao(50.00);

        CalcularValeAlime alimentacao = new CalcularValeAlime();

        assertEquals(0.0, alimentacao.calcularDesconto(f));
    }

    @Test
    void deveRetornarOValorQuandoTemVA(){
        // metodo pra validar quandoo o valor corretamente
        Funcionario f= new Funcionario();
        f.setReceberValeAlimentacao(true);
        f.setDiasTrabalhadasMes(20);
        f.setCustoDiarioAlimentacao(50.00);

        CalcularValeAlime alimentacao = new CalcularValeAlime();

        assertEquals(1000.00, alimentacao.calcularDesconto(f));
    }
}
