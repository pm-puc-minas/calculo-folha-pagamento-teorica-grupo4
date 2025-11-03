package com.trabalho.backend.controller.SerialEDesserial;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.backend.dto.FuncionarioDTO;



@RestController
@RequestMapping("/teste")

public class SeriaEDesseriaFuncionario { // essa clase será para desseralizar e serializar

    //serializar os objetos do funcionario, vai ser usado o funcionarioDTO para a melhor segurança
    @GetMapping("/func")
    public FuncionarioDTO getFuncionario(){
        FuncionarioDTO fDto= new FuncionarioDTO();
        fDto.setIdFuncionario(1L);
        fDto.setNome("Gabriel Santos");
        fDto.setCargo("Estagiário");
        fDto.setDataAdmissao(LocalDate.of(2025, 11, 14));

        return fDto;
    }

    //desserialização
    @PostMapping("/func")
    public String criarFuncionario(@RequestBody FuncionarioDTO f){
        return "Funcionario: "+ f.getNome() + "do cargo: "+f.getCargo();
    }

}
