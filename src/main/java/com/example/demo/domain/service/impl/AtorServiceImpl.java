package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Ator;
import com.example.demo.domain.model.Filme;
import com.example.demo.domain.repository.AtorRepository;
import com.example.demo.domain.repository.FilmeRepository;
import com.example.demo.domain.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtorServiceImpl implements AtorService {

    @Autowired
    private AtorRepository repository;
    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional(readOnly = false)
    @Override
    public Ator salvarAtor(Ator ator) {
        Ator atorSalvo = repository.save(ator);
        List<Filme> listaDeFilmes = new ArrayList<Filme>();
        for (int i = 0; i < ator.getFilmes().size(); i++) {
            listaDeFilmes.add(filmeRepository.findById(atorSalvo.getFilmes().get(i).getIdFilme()).orElseThrow(() -> new RegrasDeNegocioException("Não foi possível vincular um filme ao ator, pois o id do filme é inválido!")));
        }
        ;
        atorSalvo.setFilmes(listaDeFilmes);
        return atorSalvo;
    }

    @Transactional(readOnly = false)
    @Override
    public Ator atualizarAtor(Ator ator) {
        return repository.save(ator);
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarAtorPorId(Long idAtor) {
        try {
            buscarAtorPorId(idAtor);
            repository.deleteById(idAtor);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe ator com id " + idAtor + " para ser deletado!");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Ator buscarAtorPorId(Long idAtor) {
        return repository.findById(idAtor).orElseThrow(() -> new RegrasDeNegocioException("Não existe ator com id " + idAtor + "!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ator> listarTodosAtores() {
        return repository.findAll();
    }

}
