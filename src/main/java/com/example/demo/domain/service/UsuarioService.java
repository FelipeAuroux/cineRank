package com.example.demo.domain.service;

import com.example.demo.domain.model.Usuario;

import java.util.Date;
import java.util.List;

public interface UsuarioService {

    public Usuario criarNovoUsuario(Usuario usuario);
    public void deletarUsuarioPorId(Long idUsuario);
    public Usuario atualizarUsuarioPorId(Usuario usuario);
    public Usuario buscarUsuarioPorId(Long idUsuario);
    public Usuario buscarUsuarioPorNomeDeUsuario(String nomeDeUsuario);
    public List<Usuario> listarTodosUsuarios();
    public int tentativasDeLoginDoUsuario(String username);
    public Date liberarLogin(String nomeDeUsuario);
    public Date obterDataDeLiberacaoLogin(String nomeDeUsuario);
    public Boolean verificarDataDeLiberacaoLogin(String nomeDeUsuario);
    public void redefinirTentativasELiberarLogin(String nomeDeUsuario);
    public Usuario login(Usuario user);
    public int updateAttempts(String username);
}
