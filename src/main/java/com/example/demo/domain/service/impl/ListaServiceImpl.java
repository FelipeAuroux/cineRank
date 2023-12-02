package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Lista;
import com.example.demo.domain.repository.ListaRepository;
import com.example.demo.domain.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaServiceImpl implements ListaService {

    @Autowired
    private ListaRepository repository;

    @Override
    public Lista salvarLista(Lista lista) {
        return repository.save(lista);
    }

    @Override
    public Lista atualizarLista(Lista lista) {
        return repository.save(lista);
    }

    @Override
    public List<Lista> listarTodasListas() {
        return repository.findAll();
    }

    @Override
    public Lista buscarListaPorId(Long idLista) {
        return repository.findById(idLista).orElseThrow(() -> new RegrasDeNegocioException("Não existe lista com id " + idLista + "!"));
    }

    @Override
    public void deletarListaPorId(Long idLista) {
        try {
            buscarListaPorId(idLista);
            repository.deleteById(idLista);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe lista com id " + idLista + " para ser deletada!");
        }
    }
}
