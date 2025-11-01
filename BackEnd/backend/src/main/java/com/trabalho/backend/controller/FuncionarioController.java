package com.trabalho.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.backend.dto.FuncionarioDTO;
import com.trabalho.backend.event.CadastroFuncionarioEvent;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*") // permite conectar com front

public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionario;
    @Autowired
    private ApplicationEventPublisher aviso;


    // cadastrar um funcionário
    @PostMapping
    public Funcionario cadastrarFuncionario(@RequestBody Funcionario f){
        Funcionario salvo= funcionario.save(f);
        //Após o cadastro dispara o evento
        aviso.publishEvent(new CadastroFuncionarioEvent(salvo));

        return salvo;
    }

    //listar os funcionários
    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionario.findAll();
    }

    //Endpoint para mostrar somente nome e dataAdmissao no front end

    @GetMapping("/mostrarCampos")
    public List<FuncionarioDTO> listarCamposEspecifico(){
        return funcionario.findAll()
            .stream()
            .map(f-> new FuncionarioDTO(f.getIdFuncionario(), f.getNome(), f.getCargo(), f.getDataAdmissao()))
            .collect(Collectors.toList());
    }


    
}
