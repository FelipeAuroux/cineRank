package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Sessao;
import com.example.demo.domain.repository.SessaoRepository;
import com.example.demo.domain.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoServiceImpl implements SessaoService {

    @Autowired
    private SessaoRepository repository;

    @Override
    public Sessao salvarSessao(Sessao sessao) {
        return repository.save(sessao);
    }

    @Override
    public Sessao atualizarSessao(Sessao sessao) {
        return repository.save(sessao);
    }

    @Override
    public List<Sessao> listarTodasSessoes() {
        return repository.findAll();
    }

    @Override
    public Sessao buscarSessaoPorId(Long idSessao) {
        return repository.findById(idSessao).orElseThrow(() -> new RegrasDeNegocioException("Não existe sessão com id " + idSessao + "!"));

    }

    @Override
    public void deletarSessaoPorId(Long idSessao) {
        try {
            buscarSessaoPorId(idSessao);
            repository.deleteById(idSessao);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe sessão com id " + idSessao + " para ser deletada!");
        }
    }
}
