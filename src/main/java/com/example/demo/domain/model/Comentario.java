package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;
    @NotBlank
    @Size(max = 280)
    private String texto;
    @NotNull
    private Date dataHora;

    // Relacionamentos

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

}
