package com.trabalho.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trabalho.backend.model.Administrador;
import com.trabalho.backend.service.AdministradorService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdmController {

    private final AdministradorService adm;

    public AdmController(AdministradorService adm) {
        this.adm = adm;
    }

    // cadastrar um adm via JSON
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Administrador novoAdm) {
        try {
            adm.criarAdm(novoAdm.getNome(), novoAdm.getCpf(), novoAdm.getEmail(), novoAdm.getSenha());
            return ResponseEntity.ok("Administrador criado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // logar via JSON
    @PostMapping("/logar")
    public ResponseEntity<?> login(@RequestBody Administrador dadosLogin) {
        boolean autenticadoSucesso = adm.autenticar(dadosLogin.getEmail(), dadosLogin.getSenha());

        if (autenticadoSucesso) {
            return ResponseEntity.ok("Login realizado com sucesso!!");
        } else {
            return ResponseEntity.status(401).body("Email ou senha incorretos!");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(adm.listarTodos());
    }

}


    