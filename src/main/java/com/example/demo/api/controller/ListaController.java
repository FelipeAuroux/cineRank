package com.example.demo.api.controller;

import com.example.demo.api.dto.filme.FilmeResponseDTO;
import com.example.demo.api.dto.lista.ListaRequestDTO;
import com.example.demo.api.dto.lista.ListaResponseDTO;
import com.example.demo.api.mapper.ListaMapper;
import com.example.demo.domain.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lista")
public class ListaController {

    @Autowired
    private ListaService service;

    // USER

    @Operation(summary = "Cria lista", description = "Cria uma nova lista de filmes para o usuário", method = "POST", responses = {
            @ApiResponse(description = "Lista foi criada com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao criar a lista!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/novo")
    public ResponseEntity<?> salvarLista(@RequestBody @Valid ListaRequestDTO listaRequestDTO){
        return new ResponseEntity<ListaResponseDTO>(ListaMapper.converterListaEntidadeEmListaResponseDTO( service.salvarLista(ListaMapper.converterListaRequestDTOEmListaEntidade(listaRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista a lista de filme do usuário", description = "Lista a lista de filme do usuário", method = "GET", responses = {
            @ApiResponse(description = "Lista listada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ListaResponseDTO.class))),
            @ApiResponse(description = "Erro ao listar a lista de filmes do usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<?> listarTodasListas(@PathVariable("idUsuario") @Valid @NotNull(message = "O id do usuário deve ser informado!") Long idUsuario){
        return ResponseEntity.ok(service.buscarListaDoUsuarioPorId(idUsuario));
    }

    // ADMIN


}
