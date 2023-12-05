package com.example.demo.domain.repository;

import com.example.demo.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByCpf(String cpf);

    public Optional<Usuario> findByUsuario(String usuario);

    @Query("SELECT u.tentativasDeLogin FROM Usuario u WHERE u.usuario = :usuario")
    public int tentativasDoUsuario(@Param("usuario") String usuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.tentativasDeLogin = :tentativasDeLogin WHERE u.usuario = :usuario")
    public void updateAttemptsUser(@Param("tentativasDeLogin") int tentativasDeLogin, @Param("usuario") String usuario);

    @Query("SELECT u.tentativasDeLogin FROM Usuario u WHERE u.usuario = :usuario")
    public int attemptsUser(@Param("usuario") String usuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.dataDeLiberacao = :releaseDate WHERE u.usuario = :usuario")
    public void updateReleaseDate(@Param("releaseDate") Date releaseDate, @Param("usuario") String usuario);

    @Query("SELECT u.dataDeLiberacao FROM Usuario u WHERE u.usuario = :usuario")
    public Date getDateReleaseLogin(@Param("usuario") String usuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.dataDeLiberacao = null, u.tentativasDeLogin = 0 WHERE u.usuario = :usuario")
    public void resetAttemptsAndReleaseLogin(@Param("usuario") String usuario);
}
