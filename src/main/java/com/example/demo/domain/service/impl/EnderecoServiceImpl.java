package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.repository.EnderecoRepository;
import com.example.demo.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional(readOnly = false)
    @Override
    public Endereco salvarEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    @Transactional(readOnly = false)
    @Override
    public Endereco atualizarEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Endereco> listarTodosEnderecos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Endereco buscarEnderecoPorId(Long idEndereco) {
        return repository.findById(idEndereco).orElseThrow(() -> new RegrasDeNegocioException("Não existe endereço com id " + idEndereco + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarEnderecoPorId(Long idEndereco) {
        try {
            buscarEnderecoPorId(idEndereco);
            repository.deleteById(idEndereco);
        } catch (RegrasDeNegocioException regrasDeNegocioException) {
            throw new RegrasDeNegocioException("Não existe endereço com id " + idEndereco + " para ser deletado!");
        }
    }

}
