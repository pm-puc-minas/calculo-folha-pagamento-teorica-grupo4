package com.trabalho.backend.controller.SerialEDesserial;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.backend.dto.FolhaPagamentoDTO;


@RestController
@RequestMapping("/folha")
public class SeriaEDesseriaFolhaPagamento {

    //serializar os objetos da folha de Paggamento, vai ser usado o FolhaPagamentoDTO para a melhor segurança
    @GetMapping("/folhaSerial")
    public FolhaPagamentoDTO getFuncionario(){
        FolhaPagamentoDTO fDto= new FolhaPagamentoDTO();
        fDto.setIdFolhaPagamento(1L);
        fDto.setDataGeracao(LocalDate.now());
        fDto.setSalarioLiquido(1500.00);
        return fDto;
    }

    //desserialização
    @PostMapping("/folhaDesserial")
    public String criarFolha(@RequestBody FolhaPagamentoDTO f){
        return "FolhaPagamento: "+ f.getIdFolhaPagamento() + " e: "+f.getSalarioLiquido()+ " data: "+ f.getDataGeracao();
    }

}
