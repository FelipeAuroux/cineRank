package com.example.demo.domain.model;

import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCinema;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotBlank
    private String funcionamento;
    @NotNull
    private int salas;
    @CNPJ
    private String cnpj;

    // Relacionamentos

    @ManyToMany(mappedBy = "cinemas")
    private List<Sessao> sessoes;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
