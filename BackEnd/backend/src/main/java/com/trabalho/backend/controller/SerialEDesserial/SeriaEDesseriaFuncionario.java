package com.trabalho.backend.controller.SerialEDesserial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho.backend.dto.FuncionarioDTO;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.repository.FuncionarioRepository;

import java.io.File;

@RestController
@RequestMapping("/SerialeDesserial")
public class SeriaEDesseriaFuncionario { 

    private static final String caminho = "funcionario.json";
    private final FuncionarioRepository funcRepor;

    public SeriaEDesseriaFuncionario(FuncionarioRepository funcRepor) {
        this.funcRepor = funcRepor;
    }

    //serializar
    @GetMapping("/salvar/{id}")
    public String salvarFuncionario(@PathVariable Long id) {
        try {
            // buscar funcionário no banco
            Funcionario funcionario = funcRepor.findById(id).orElse(null);

            if (funcionario == null) {
                return "Funcionário não encontrado.";
            }

            // converter para DTO
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setIdFuncionario(funcionario.getIdFuncionario());
            dto.setNome(funcionario.getNome());
            dto.setCargo(funcionario.getCargo());
            dto.setDataAdmissao(funcionario.getDataAdmissao());

            // salvar como JSON no arquivo
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules(); // 
            mapper.writeValue(new File(caminho), dto);

            return "Funcionário salvo no arquivo JSON com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar: " + e.getMessage();
        }
    }

    //desserializar
    @GetMapping("/carregar")
    public FuncionarioDTO carregarFuncionario() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules(); 
            return mapper.readValue(new File(caminho), FuncionarioDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}

