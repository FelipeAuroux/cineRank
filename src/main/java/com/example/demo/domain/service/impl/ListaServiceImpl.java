package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Filme;
import com.example.demo.domain.model.Lista;
import com.example.demo.domain.repository.FilmeRepository;
import com.example.demo.domain.repository.ListaRepository;
import com.example.demo.domain.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaServiceImpl implements ListaService {

    @Autowired
    private ListaRepository repository;
    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional(readOnly = false)
    @Override
    public Lista salvarLista(Lista lista) {
        Lista listaSalva = repository.save(lista);
        List<Filme> filmes = new ArrayList<Filme>();
        for (int i = 0; i < listaSalva.getFilmes().size(); i++) {
            filmes.add(filmeRepository.findById(listaSalva.getFilmes().get(i).getIdFilme())
                    .orElseThrow(() -> new RegrasDeNegocioException("Você tentou adicionar um filme a sua lista com id inválido!")));
        }
        listaSalva.setFilmes(filmes);
        return listaSalva;
    }

    @Transactional(readOnly = false)
    @Override
    public Lista atualizarLista(Lista lista) {
        return repository.save(lista);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Lista> listarTodasListas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Lista buscarListaPorId(Long idLista) {
        return repository.findById(idLista).orElseThrow(() -> new RegrasDeNegocioException("Não existe lista com id " + idLista + "!"));
    }

    @Transactional(readOnly = false)
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
