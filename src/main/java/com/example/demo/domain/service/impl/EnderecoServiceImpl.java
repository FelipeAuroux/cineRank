package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.repository.EnderecoRepository;
import com.example.demo.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Override
    public Endereco salvarEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public Endereco atualizarEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public List<Endereco> listarTodosEnderecos() {
        return repository.findAll();
    }

    @Override
    public Endereco buscarEnderecoPorId(Long idEndereco) {
        return repository.findById(idEndereco).orElseThrow(() -> new RegrasDeNegocioException("Não existe endereço com id " + idEndereco + "!"));
    }

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
