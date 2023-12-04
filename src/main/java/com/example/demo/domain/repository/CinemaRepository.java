package com.example.demo.domain.repository;

import com.example.demo.domain.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    public Optional<Cinema> findByCnpj(String cnpj);
}
