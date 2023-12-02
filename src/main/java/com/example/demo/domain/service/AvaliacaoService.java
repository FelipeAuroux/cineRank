package com.example.demo.domain.service;

import com.example.demo.domain.model.Avaliacao;

import java.util.List;

public interface AvaliacaoService {

    public Avaliacao salvarAvaliacao(Avaliacao avaliacao);

    public Avaliacao atualizarAvaliacao(Avaliacao avaliacao);

    public List<Avaliacao> listarTodasAvaliacoes();

    public Avaliacao buscarAvaliacaoPorId(Long idAvaliacao);

    public void deletarAvaliacaoPorId(Long idAvaliacao);

}
