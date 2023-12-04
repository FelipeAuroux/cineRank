package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Comentario;
import com.example.demo.domain.model.Filme;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.ComentarioRepository;
import com.example.demo.domain.repository.FilmeRepository;
import com.example.demo.domain.repository.UsuarioRepository;
import com.example.demo.domain.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository repository;
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = false)
    @Override
    public Comentario comentarFilme(Comentario comentario) {
        Comentario comentarioSalvo = repository.save(comentario);
        Filme filme = filmeRepository.findById(comentarioSalvo.getFilme().getIdFilme()).orElseThrow(() -> new RegrasDeNegocioException("Não foi possível vincular o filme ao comentário, pois o id do filme é inválido!"));
        Usuario usuario = usuarioRepository.findById(comentarioSalvo.getUsuario().getIdUsuario()).orElseThrow(() -> new RegrasDeNegocioException("Não foi possível vincular o usuário ao comentário, pois o id do usuário é inválido!"));
        comentarioSalvo.setFilme(filme);
        comentarioSalvo.setUsuario(usuario);
        return comentarioSalvo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comentario> listarTodosComentarios() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comentario buscarComentarioPorId(Long idComentario) {
        return repository.findById(idComentario).orElseThrow(() -> new RegrasDeNegocioException("Não existe comentário com id " + idComentario + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarComentarioPorId(Long idComentario) {
        try {
            buscarComentarioPorId(idComentario);
            repository.deleteById(idComentario);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe comentário com id " + idComentario + " para ser deletado!");
        }
    }

}
