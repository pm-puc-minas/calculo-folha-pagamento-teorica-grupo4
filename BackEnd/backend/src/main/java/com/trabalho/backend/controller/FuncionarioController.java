package com.trabalho.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*") // permite conectar com front

public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionario;

    // cadastrar um funcionário
    @PostMapping
    public Funcionario cadastrarFuncionario(@RequestBody Funcionario f){
        return funcionario.save(f);
    }

    //listar os funcionários
    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionario.findAll();
    }


    
}
