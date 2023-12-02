package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Cinema;
import com.example.demo.domain.repository.CinemaRepository;
import com.example.demo.domain.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository repository;

    @Override
    public Cinema salvarCinema(Cinema cinema) {
        return repository.save(cinema);
    }

    @Override
    public Cinema atualizarCinema(Cinema cinema) {
        return repository.save(cinema);
    }

    @Override
    public List<Cinema> listarTodosCinemas() {
        return repository.findAll();
    }

    @Override
    public Cinema buscarCinemaPorId(Long idCinema) {
        return repository.findById(idCinema).orElseThrow(() -> new RegrasDeNegocioException("Não existe cinema com id " + idCinema + "!"));
    }

    @Override
    public void deletarCinemaPorId(Long idCinema) {
        try {
            buscarCinemaPorId(idCinema);
            repository.deleteById(idCinema);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe cinema com id " + idCinema + " para ser deletado!");
        }
    }
}
