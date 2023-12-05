package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Filme;
import com.example.demo.domain.model.Lista;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.FilmeRepository;
import com.example.demo.domain.repository.ListaRepository;
import com.example.demo.domain.repository.UsuarioRepository;
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
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = false)
    @Override
    public Lista salvarLista(Lista lista) {
        Usuario usuario = usuarioRepository.findById(lista.getUsuario().getIdUsuario()).orElseThrow(() -> new RegrasDeNegocioException("Não foi possível criar a lista para o usuário, pois seu id é invalido!"));
        if (usuario.getLista() == null) {
            // usuário não tem uma lista
            List<Filme> filmes = new ArrayList<Filme>();
            for (int i = 0; i < lista.getFilmes().size(); i++) {
                filmes.add(filmeRepository.findById(lista.getFilmes().get(i).getIdFilme())
                        .orElseThrow(() -> new RegrasDeNegocioException("Você tentou adicionar um filme a sua lista com id inválido!")));
            }
            Lista listaSalva = repository.save(lista);
            listaSalva.setUsuario(usuario);
            listaSalva.setFilmes(filmes);
            return listaSalva;
        }else{
            // usuário já tem uma lista, não pode ter outra
            throw new RegrasDeNegocioException("O usuário "+usuario.getNome()+" já tem uma lista criada em "+usuario.getLista().getAdicao()+"!");
        }
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

    @Transactional(readOnly = true)
    @Override
    public Lista buscarListaDoUsuarioPorId(Long idUsuario) {
        return repository.findListaByIdUsuario(idUsuario).orElseThrow(() -> new RegrasDeNegocioException("Não existe uma lista para o usuário de id "+idUsuario));
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
