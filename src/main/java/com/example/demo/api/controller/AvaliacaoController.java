package com.example.demo.api.controller;

import com.example.demo.api.dto.avaliacao.AvaliacaoRequestDTO;
import com.example.demo.api.dto.avaliacao.AvaliacaoResponseDTO;
import com.example.demo.api.dto.cinema.CinemaResponseDTO;
import com.example.demo.api.mapper.AvaliacaoMapper;
import com.example.demo.domain.service.AvaliacaoService;
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
@RequestMapping(path = "/avaliacao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Avaliação")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    // USER

    @Operation(summary = "Cadastra avaliação", description = "Realiza o cadastro de avaliação", method = "POST", responses = {
            @ApiResponse(description = "Avaliação realizada com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AvaliacaoResponseDTO.class))),
            @ApiResponse(description = "Não foi possível cadastrar a avaliação!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/novo")
    public ResponseEntity<?> fazerAvaliacao(@RequestBody @Valid AvaliacaoRequestDTO avaliacaoRequestDTO){
        return new ResponseEntity<AvaliacaoResponseDTO>(AvaliacaoMapper.converterAvaliacaoEntidadeEmAvaliacaoResponseDTO(service.salvarAvaliacao(AvaliacaoMapper.converterAvaliacaoRequestDTOEmAvaliacaoEntidade(avaliacaoRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista avaliações", description = "Realiza a listagem de todas avaliações", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AvaliacaoResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar as avaliações!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/listar-todas")
    public ResponseEntity<?> listarTodasAvaliacoes(){
        return ResponseEntity.ok(AvaliacaoMapper.converterListaDeAvaliacaoEntidadeParaListaDeAvaliacaoResponseDTO(service.listarTodasAvaliacoes()));
    }

    // ADMIN

    @Operation(summary = "Deleta uma avaliação", description = "Realiza a remoção de uma avaliação pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível remover uma avaliação", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/deletar/{idAvaliacao}")
    public ResponseEntity<?> deletarAvaliacao(@PathVariable("idAvaliacao") @Valid @NotNull(message = "O id da avaliação deve ser informado!") Long idAvaliacao) {
        service.deletarAvaliacaoPorId(idAvaliacao);
        return ResponseEntity.noContent().build();
    }


}
