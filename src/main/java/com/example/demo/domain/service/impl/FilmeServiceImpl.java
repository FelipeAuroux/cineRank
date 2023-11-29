package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Filme;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.FilmeRepository;
import com.example.demo.domain.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeServiceImpl implements FilmeService {

    @Autowired
    private FilmeRepository repository;

    @Override
    public Filme adicionarNovoFilme(Filme filme) {
        // Verificar se o filme não está cadastrado
        if (repository.findFilmeByTitulo(filme.getTitulo()).isPresent()) {
            throw new RegrasDeNegocioException("O filme " + filme.getTitulo() + " já está cadastrado em nosso sistema!");
        } else {
            return repository.save(filme);
        }
    }

    @Override
    public void deletarFilmePorId(Long idFilme) {
        repository.deleteById(idFilme);
    }

    @Override
    public List<Filme> listarTodosFilmes() {
        return repository.findAll();
    }
}
