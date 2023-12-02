package com.example.demo.domain.service;

import com.example.demo.domain.model.Lista;

import java.util.List;

public interface ListaService {
    public Lista salvarLista(Lista lista);

    public Lista atualizarLista(Lista lista);

    public List<Lista> listarTodasListas();

    public Lista buscarListaPorId(Long idLista);

    public void deletarListaPorId(Long idLista);
}
