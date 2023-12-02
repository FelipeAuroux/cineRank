package com.example.demo.domain.service;

import com.example.demo.domain.model.Ator;

import java.util.List;

public interface AtorService {

    public Ator salvarAtor(Ator ator);

    public Ator atualizarAtor(Ator ator);

    public void deletarAtorPorId(Long idAtor);

    public Ator buscarAtorPorId(Long idAtor);

    public List<Ator> listarTodosAtores();


}
