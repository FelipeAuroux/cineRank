package com.example.demo.domain.service;

import com.example.demo.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {

    public Usuario criarNovoUsuario(Usuario usuario);
    public void deletarUsuarioPorId(Long idUsuario);
    public Usuario atualizarUsuarioPorId(Usuario usuario);
    public Usuario buscarUsuarioPorId(Long idUsuario);
    public List<Usuario> listarTodosUsuarios();

}
