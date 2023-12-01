package com.example.demo.domain.service.impl;

import com.example.demo.domain.model.Comentario;
import com.example.demo.domain.repository.ComentarioRepository;
import com.example.demo.domain.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Override
    public Comentario comentarFilme(Comentario comentario) {
        return repository.save(comentario);
    }

    @Override
    public List<Comentario> listarTodosComentarios() {
        return repository.findAll();
    }
}
