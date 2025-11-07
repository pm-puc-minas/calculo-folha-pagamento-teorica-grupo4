package com.trabalho.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import com.trabalho.backend.model.Administrador;
import com.trabalho.backend.repository.AdministradorRepository;
import com.trabalho.backend.exception.DadosInvalidosException;

@Service
public class AdministradorService {

    private final AdministradorRepository admRepor;
    private final BCryptPasswordEncoder senhaCriptografada = new BCryptPasswordEncoder();

    public AdministradorService(AdministradorRepository admRepor) {
        this.admRepor = admRepor;
    }

    // Registrar novo administrador
    public Administrador criarAdm(String nome, String cpf, String email, String senha) {

        // validações simples
        if (nome == null || nome.isBlank() ||
            cpf == null || cpf.isBlank() ||
            email == null || email.isBlank() ||
            senha == null || senha.isBlank()) {

            throw new DadosInvalidosException("Dados inválidos: nome, cpf, email ou senha não podem estar vazios");
        }

        if (admRepor.existsByEmail(email)) {
            throw new DadosInvalidosException("Já existe um Administrador cadastrado com este email");
        }

        Administrador novoAdm = new Administrador();
        novoAdm.setNome(nome);
        novoAdm.setCpf(cpf);
        novoAdm.setEmail(email);
        novoAdm.setSenha(senhaCriptografada.encode(senha));

        return admRepor.save(novoAdm);
    }

    // Login
    public boolean autenticar(String email, String senha) {
        Optional<Administrador> opt = admRepor.findByEmail(email);

        if (opt.isEmpty()) {
            return false; // email não encontrado
        }

        Administrador admin = opt.get();

        return senhaCriptografada.matches(senha, admin.getSenha());
    }

    // Listar todos
    public List<Administrador> listarTodos() {
        return admRepor.findAll();
    }
}


