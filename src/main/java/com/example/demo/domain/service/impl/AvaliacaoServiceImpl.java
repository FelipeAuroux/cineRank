package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Avaliacao;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.AvaliacaoRepository;
import com.example.demo.domain.repository.UsuarioRepository;
import com.example.demo.domain.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = false)
    @Override
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        if (repository.findByUsuarioId(avaliacao.getUsuario().getIdUsuario()).isEmpty()) {
            Avaliacao avaliacaoSalva = repository.save(avaliacao);
            Usuario usuario = usuarioRepository.findById(avaliacaoSalva.getUsuario().getIdUsuario()).orElseThrow(() -> new RegrasDeNegocioException("Não foi possível vincular o usuário para a avaliação, pois o id do usuário é inválido!"));
            avaliacaoSalva.setUsuario(usuario);
            return avaliacaoSalva;
        } else {
            throw new RegrasDeNegocioException("O usuário de id " + avaliacao.getUsuario().getIdUsuario() + " já realizou uma avaliação sobre o filme de id " + avaliacao.getFilme().getIdFilme());
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Avaliacao atualizarAvaliacao(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Avaliacao> listarTodasAvaliacoes() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Avaliacao buscarAvaliacaoPorId(Long idAvaliacao) {
        return repository.findById(idAvaliacao).orElseThrow(() -> new RegrasDeNegocioException("Não existe avaliação com id " + idAvaliacao + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarAvaliacaoPorId(Long idAvaliacao) {
        try {
            buscarAvaliacaoPorId(idAvaliacao);
            repository.deleteById(idAvaliacao);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe avaliação com id " + idAvaliacao + " para ser deletada!");
        }
    }
}
