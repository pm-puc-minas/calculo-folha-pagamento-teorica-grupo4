package com.trabalho.backend.controller.SerialEDesserial;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho.backend.dto.FolhaPagamentoDTO;
import com.trabalho.backend.model.FolhaPagamento;
import com.trabalho.backend.repository.FolhaPagamentoRepository;

@RestController
@RequestMapping("/folhaSerial")
public class SeriaEDesseriaFolhaPagamento {

    private static final String caminho = "folha_pagamento.json";
    private final FolhaPagamentoRepository folhaRepo;

    public SeriaEDesseriaFolhaPagamento(FolhaPagamentoRepository folhaRepo) {
        this.folhaRepo = folhaRepo;
    }

    // serializar
    @GetMapping("/salvar/{id}")
    public String salvarFolha(@PathVariable Long id) {
        try {
            FolhaPagamento folha = folhaRepo.findById(id).orElse(null);

            if (folha == null) {
                return "Folha de pagamento não encontrada.";
            }

            FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
            dto.setIdFolhaPagamento(folha.getIdFolhaPagamento());
            dto.setDataGeracao(folha.getGeracaoData());
            dto.setSalarioLiquido(folha.getSalarioLiquido());

            // salvar no arquivo
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules(); // Suporte para LocalDate
            mapper.writeValue(new File(caminho), dto);

            return "Folha de pagamento salva em JSON com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar: " + e.getMessage();
        }
    }

    // desserializar
    @GetMapping("/carregar")
    public FolhaPagamentoDTO carregarFolha() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules(); // Suporte para LocalDate
            return mapper.readValue(new File(caminho), FolhaPagamentoDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}

