package com.example.demo.api.controller;

import com.example.demo.api.dto.filme.FilmeRequestDTO;
import com.example.demo.api.dto.filme.FilmeResponseDTO;
import com.example.demo.api.message.MensagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/filmes")
@Tag(name = "Filme")
public class FilmeController {

    @PostMapping("/novo")
    @Operation(summary = "Cadastra filmes", description = "Salva um novo filme", method = "POST", responses = {
            @ApiResponse(description = "Filme cadastrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FilmeResponseDTO.class))),
            @ApiResponse(description = "Erro ao cadastrar filme!", responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MensagemResponse.class)))
    })
    public ResponseEntity criarNovoFilme(@RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        return ResponseEntity.ok("Tudo certo");
    }

}
