package com.trabalho.backend.controller.SerialEDesserial;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho.backend.dto.FolhaPagamentoDTO;
import com.trabalho.backend.model.FolhaPagamento;
import com.trabalho.backend.repository.FolhaPagamentoRepository;

@RestController
@RequestMapping("/folhaSerial")
public class SeriaEDesseriaFolhaPagamento {

    private static final String CAMINHO_ARQUIVO = "folha_pagamento.json";
    private final FolhaPagamentoRepository folhaRepo;

    private final ObjectMapper mapper;

    public SeriaEDesseriaFolhaPagamento(FolhaPagamentoRepository folhaRepo) {
        this.folhaRepo = folhaRepo;
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules(); // necessário para LocalDate
    }

    /**
     * 
     * @param id Id da FolhaPagamento
     * @return mensagem de sucesso ou erro
     */
    @GetMapping("/salvar/{id}")
    public String salvarFolha(@PathVariable Long id) {
        FolhaPagamento folha = folhaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Folha de pagamento não encontrada"));

        
        FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
        dto.setIdFolhaPagamento(folha.getIdFolhaPagamento());
        dto.setDataGeracao(folha.getGeracaoData());
        dto.setSalarioLiquido(folha.getSalarioLiquido());

        try {
            mapper.writeValue(new File(CAMINHO_ARQUIVO), dto);
            return "Folha de pagamento salva em JSON com sucesso!";
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Desserializa o arquivo JSON e retorna como DTO.
     * @return FolhaPagamentoDTO
     */
    @GetMapping("/carregar")
    public FolhaPagamentoDTO carregarFolha() {
        try {
            return mapper.readValue(new File(CAMINHO_ARQUIVO), FolhaPagamentoDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao carregar JSON: " + e.getMessage(), e);
        }
    }
}


