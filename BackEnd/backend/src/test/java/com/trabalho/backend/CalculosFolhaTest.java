package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when; //aprendi que como o meu serice usa repositorio entao tem qu usar mockito

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trabalho.backend.model.FolhaPagamento;
import com.trabalho.backend.repository.FolhaPagamentoRepository;
import com.trabalho.backend.service.FolhaPagamentoService;

public class CalculosFolhaTest {

    @Mock
    private FolhaPagamentoRepository folhaRepo;

    @InjectMocks
    private FolhaPagamentoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // cria a folha para servir como base
    private FolhaPagamento folha(double salarioliquido, double va, double vt, double inss) {
        FolhaPagamento f = new FolhaPagamento();
        f.setSalarioLiquido(salarioliquido);
        f.setVA(va);
        f.setVT(vt);
        f.setInss(inss);
        return f;
    }

    @Test
    void deveCalcularMediaSalarioGeral() {
        List<FolhaPagamento> folhas = Arrays.asList(
                folha(1000, 0, 0, 0),
                folha(2000, 0, 0, 0),
                folha(3000, 0, 0, 0)
        );

        when(folhaRepo.findAll()).thenReturn(folhas);

        Double resultado = service.calcularMediaSalarioGeral();

        assertEquals(2000.0, resultado);
    }

    @Test
    void temQueRetornarZero() {
        when(folhaRepo.findAll()).thenReturn(Collections.emptyList());

        Double resultado = service.calcularMediaSalarioGeral();

        assertEquals(0.0, resultado);
    }

    @Test
    void temQueSomarTodosValeAlimentacao() {
        when(folhaRepo.findAll()).thenReturn(Arrays.asList(
                folha(0, 200, 0, 0),
                folha(0, 300, 0, 0)
        ));

        Double resultado = service.somarTodosValeAlimentacao();

        assertEquals(500.0, resultado);
    }

    @Test
    void temQueSomarTodosValeTransporte() {
        when(folhaRepo.findAll()).thenReturn(Arrays.asList(
                folha(0, 0, 150, 0),
                folha(0, 0, 100, 0)
        ));

        Double resultado = service.somarTodosValeTransporte();

        assertEquals(250.0, resultado);
    }

    @Test
    void deveSomarTodosINSS() {
        when(folhaRepo.findAll()).thenReturn(Arrays.asList(
                folha(0, 0, 0, 80),
                folha(0, 0, 0, 120)
        ));

        Double resultado = service.somarTodosINSS();

        assertEquals(200.0, resultado);
    }
    
}
