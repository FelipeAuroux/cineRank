package com.example.demo.domain.repository;

import com.example.demo.domain.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    public Optional<Filme> findFilmeByTitulo(String titulo);
}
