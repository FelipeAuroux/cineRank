package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.EnderecoRepository;
import com.example.demo.domain.repository.UsuarioRepository;
import com.example.demo.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = false)
    @Override
    public Usuario criarNovoUsuario(Usuario usuario) {
        // Verificar se já existe esse usuário, se não existir então salva, caso contrário gera mensagem de aviso
        if (repository.findByCpf(usuario.getCpf()).isPresent()) {
            // Existe
            throw new RegrasDeNegocioException("O usuário com cpf " + usuario.getCpf() + " já está cadastrado em nosso sistema!");
        } else {
            Endereco endereco = usuario.getEndereco();
            usuario.setEndereco(null);
            Usuario usuarioSalvo = repository.save(usuario);
            endereco.setUsuario(usuarioSalvo);
            Endereco enderecoSalvo = enderecoRepository.save(endereco);
            usuarioSalvo.setEndereco(enderecoSalvo);
            return usuarioSalvo;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarUsuarioPorId(Long idUsuario) {
        repository.deleteById(idUsuario);
    }

    @Transactional(readOnly = false)
    @Override
    public Usuario atualizarUsuarioPorId(Usuario usuario) {
        if (repository.findById(usuario.getIdUsuario()).isPresent()) {
            // Existe o usuario para atualizar
            return repository.save(usuario);
        } else {
            // Não existe o usuário para atualizar
            throw new RegrasDeNegocioException("Não existe usuário com id " + usuario.getIdUsuario());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return repository.findById(idUsuario).orElseThrow(() -> new RegrasDeNegocioException("Não existe usuário com id " + idUsuario));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarTodosUsuarios() {
        return repository.findAll();
    }

}
