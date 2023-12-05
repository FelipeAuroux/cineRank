package com.example.demo.domain.repository;

import com.example.demo.domain.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {

    @Query("SELECT l FROM Lista l WHERE l.usuario.idUsuario = :usuario_id")
    public Optional<Lista> findListaByIdUsuario(@Param("usuario_id") Long usuario_id);
}
