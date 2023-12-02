package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Ator;
import com.example.demo.domain.repository.AtorRepository;
import com.example.demo.domain.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorServiceImpl implements AtorService {

    @Autowired
    private AtorRepository repository;

    @Override
    public Ator salvarAtor(Ator ator) {
        return repository.save(ator);
    }

    @Override
    public Ator atualizarAtor(Ator ator) {
        return repository.save(ator);
    }

    @Override
    public void deletarAtorPorId(Long idAtor) {
        try {
            buscarAtorPorId(idAtor);
            repository.deleteById(idAtor);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe ator com id " + idAtor + " para ser deletado!");
        }

    }

    @Override
    public Ator buscarAtorPorId(Long idAtor) {
        return repository.findById(idAtor).orElseThrow(() -> new RegrasDeNegocioException("Não existe ator com id " + idAtor + "!"));
    }

    @Override
    public List<Ator> listarTodosAtores() {
        return repository.findAll();
    }
}
