package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Avaliacao;
import com.example.demo.domain.model.Filme;
import com.example.demo.domain.model.Sessao;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.AvaliacaoRepository;
import com.example.demo.domain.repository.FilmeRepository;
import com.example.demo.domain.repository.UsuarioRepository;
import com.example.demo.domain.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        Avaliacao avaliacaoSalva = repository.save(avaliacao);
        Usuario usuario = usuarioRepository.findById(avaliacaoSalva.getUsuario().getIdUsuario()).get();
        avaliacaoSalva.setUsuario(usuario);
        return avaliacaoSalva;
    }

    @Override
    public Avaliacao atualizarAvaliacao(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    @Override
    public List<Avaliacao> listarTodasAvaliacoes() {
        return repository.findAll();
    }

    @Override
    public Avaliacao buscarAvaliacaoPorId(Long idAvaliacao) {
        return repository.findById(idAvaliacao).orElseThrow(() -> new RegrasDeNegocioException("Não existe avaliação com id " + idAvaliacao +"!"));
    }

    @Override
    public void deletarAvaliacaoPorId(Long idAvaliacao) {
        try{
            buscarAvaliacaoPorId(idAvaliacao);
            repository.deleteById(idAvaliacao);
        }catch(RegrasDeNegocioException regrasDeNegocioException){
            throw new RegrasDeNegocioException("Não existe avaliação com id " + idAvaliacao + " para ser deletada!");
        }
    }
}
