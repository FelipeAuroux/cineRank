package com.example.demo.domain.service;

import com.example.demo.domain.model.Comentario;

import java.util.List;

public interface ComentarioService {

    public Comentario comentarFilme(Comentario comentario);
    public List<Comentario> listarTodosComentarios();
}
