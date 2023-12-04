package com.example.demo.api.controller;

import com.example.demo.api.dto.cinema.CinemaRequestDTO;
import com.example.demo.api.dto.cinema.CinemaResponseDTO;
import com.example.demo.api.mapper.CinemaMapper;
import com.example.demo.domain.service.CinemaService;
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
@RequestMapping(path = "/cinema", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Cinema")
public class CinemaController {

    @Autowired
    private CinemaService service;

    // USER
    @Operation(summary = "Lista cinemas", description = "Realiza a listagem de todos cinemas", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CinemaResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar os cinemas!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/listar-todos")
    public ResponseEntity<?> listarCinemas(){
        return ResponseEntity.ok(CinemaMapper.converterListaDeCinemaEntidadeParaListaDeCinemaResponseDTO(service.listarTodosCinemas()));
    }

    // ADMIN

    @Operation(summary = "Cadastra cinema", description = "Realiza o cadastro de cinema", method = "POST", responses = {
            @ApiResponse(description = "Cadastro realizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CinemaResponseDTO.class))),
            @ApiResponse(description = "Não foi possível cadastrar o cinema!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/novo")
    public ResponseEntity<?> adicionarCinema(@RequestBody @Valid CinemaRequestDTO cinemaRequestDTO){
        return new ResponseEntity<CinemaResponseDTO>(CinemaMapper.converterCinemaEntidadeEmCinemaResponseDTO(service.salvarCinema(CinemaMapper.converterCinemaRequestDTOEmCinemaEntidade(cinemaRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta um cinema", description = "Realiza a remoção de um cinema pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível remover um filme", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/deletar-cinema/{idCinema}")
    public ResponseEntity<?> deletarCinema(@PathVariable("idCinema") @Valid @NotNull(message = "Informe o id do cinema!") Long idCinema){
        service.deletarCinemaPorId(idCinema);
        return ResponseEntity.noContent().build();
    }


}
