package com.example.demo.api.controller;

import com.example.demo.api.dto.filme.FilmeRequestDTO;
import com.example.demo.api.dto.filme.FilmeResponseDTO;
import com.example.demo.api.mapper.FilmeMapper;
import com.example.demo.api.message.MensagemResponse;
import com.example.demo.domain.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/filmes")
@Tag(name = "Filme")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @PostMapping("/novo")
    @Operation(summary = "Cadastra filmes", description = "Salva um novo filme", method = "POST", responses = {
            @ApiResponse(description = "Filme cadastrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FilmeResponseDTO.class))),
            @ApiResponse(description = "Erro ao cadastrar filme!", responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MensagemResponse.class)))
    })
    public ResponseEntity<?> criarNovoFilme(@RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        return new ResponseEntity<FilmeResponseDTO>(FilmeMapper.converterFilmeEntidadeParaFilmeResponseDTO(service.adicionarNovoFilme(FilmeMapper.converterFilmeRequestDTOParaFilmeEntidade(filmeRequestDTO))), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarFilmes(){
        return ResponseEntity.ok(FilmeMapper.converterListaDeFilmeEntidadeParaListaDeFilmeResponseDTO(service.listarTodosFilmes()));
    }

    @DeleteMapping("/deletar/{idFilme}")
    public ResponseEntity<?> deletarFilmePorId(@PathVariable("idFilme") @Valid Long idFilme){
        service.deletarFilmePorId(idFilme);
        return ResponseEntity.noContent().build();
    }

}
