package com.trabalho.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trabalho.backend.dto.FuncionarioDTO;
import com.trabalho.backend.event.CadastroFuncionarioEvent;
import com.trabalho.backend.exception.DadosInvalidosException;
import com.trabalho.backend.exception.ValoresBordasException;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.repository.FolhaPagamentoRepository;
import com.trabalho.backend.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionario;
    @Autowired
    private ApplicationEventPublisher aviso;
    @Autowired
    private FolhaPagamentoRepository folhaRepor;

    // cadastrar um funcionário
    @PostMapping
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario f){

        Funcionario salvo = funcionario.save(f);

        // dispara evento após cadastro
        aviso.publishEvent(new CadastroFuncionarioEvent(salvo));

        return ResponseEntity.status(201).body(salvo);
    }

    // listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){
        List<Funcionario> lista = funcionario.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    //deletar um funcionario
    @DeleteMapping("/deletarFuncionario/{idFuncionario}")
    public ResponseEntity<String> removerFuncionario(@PathVariable Long idFuncionario) {
        // o funcionario existe?
        if(funcionario.existsById(idFuncionario)){
            funcionario.deleteById(idFuncionario);
            return ResponseEntity.ok("Funcionario Removido com sucesso");
        } else {
            return ResponseEntity.status(404).body("Funcionário não encontrado!!");
        }
    }

    // listar campos específicos (para exibição no front)
    @GetMapping("/mostrarCampos")
    public ResponseEntity<List<FuncionarioDTO>> listarCamposEspecifico(){
        List<FuncionarioDTO> lista = funcionario.findAll()
            .stream()
            .map(f -> new FuncionarioDTO(
                    f.getIdFuncionario(),
                    f.getNome(),
                    f.getCargo(),
                    f.getDataAdmissao(),
                    folhaRepor.findByFuncionario(f).isPresent()
            ))

            .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(lista);
    }
}

