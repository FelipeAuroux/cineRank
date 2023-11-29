package com.example.demo.domain.service;

import com.example.demo.domain.model.Filme;

import java.util.List;

public interface FilmeService {

    public Filme adicionarNovoFilme(Filme filme);
    public void deletarFilmePorId(Long idFilme);

    public List<Filme> listarTodosFilmes();

}
