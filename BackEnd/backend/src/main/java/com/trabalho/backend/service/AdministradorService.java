package com.trabalho.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import com.trabalho.backend.model.Administrador;
import com.trabalho.backend.repository.AdministradorRepository;

@Service
public class AdministradorService {

    private final AdministradorRepository admRepor;
    private final BCryptPasswordEncoder senhaCriptografada = new BCryptPasswordEncoder();

    public AdministradorService(AdministradorRepository admRepor) {
        this.admRepor = admRepor;
    }

    // método para registrar o adm do sistema RH
    public Administrador criarAdm(String nome, String cpf, String email, String senha) {

        if (admRepor.existsByEmail(email)) {
            throw new IllegalArgumentException("Já existe um Administrador com esse email");
        }

        Administrador novoAdm = new Administrador();
        novoAdm.setNome(nome);
        novoAdm.setCpf(cpf);
        novoAdm.setEmail(email);
        novoAdm.setSenha(senhaCriptografada.encode(senha));

        return admRepor.save(novoAdm);
    }

    // validação do Login
    public boolean autenticar(String email, String senha) {
        Optional<Administrador> opt = admRepor.findByEmail(email);

        if (opt.isEmpty()) {
            return false; // email não encontrado
        }

        Administrador admin = opt.get();

        // comparar senha digitada com senha criptografada
        return senhaCriptografada.matches(senha, admin.getSenha());
    }

    public List<Administrador> listarTodos() {
        return admRepor.findAll();
    }

}

