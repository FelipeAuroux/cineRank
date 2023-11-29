package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.UsuarioRepository;
import com.example.demo.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario salvar(Usuario usuario) {
        // Verificar se já existe esse usuário, se não existir então salva, caso contrário gera mensagem de aviso
        if (repository.findByCpf(usuario.getCpf()).isPresent()) {
            // Existe
            throw new RegrasDeNegocioException("O usuário com cpf " + usuario.getCpf() + " já está cadastrado em nosso sistema!");
        } else {
            return repository.save(usuario);
        }
    }

}
