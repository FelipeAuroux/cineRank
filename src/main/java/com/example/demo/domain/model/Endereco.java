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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    @NotBlank
    private String rua;
    private String numero;
    @NotBlank
    private String bairro;
    @NotNull
    @Size(min = 9, max = 9)
    private String cep;
    @NotBlank
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

    // Relaciona mas não retorna no json

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
