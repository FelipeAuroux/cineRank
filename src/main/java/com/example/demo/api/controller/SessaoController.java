package com.example.demo.api.controller;

import com.example.demo.api.dto.cinema.CinemaRequestDTO;
import com.example.demo.api.dto.cinema.CinemaResponseDTO;
import com.example.demo.api.dto.login.LoginResponseDTO;
import com.example.demo.api.dto.sessao.SessaoRequestDTO;
import com.example.demo.api.dto.sessao.SessaoResponseDTO;
import com.example.demo.api.mapper.CinemaMapper;
import com.example.demo.api.mapper.SessaoMapper;
import com.example.demo.domain.service.SessaoService;
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
@RequestMapping(path = "/sessao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sessao")
public class SessaoController {

    @Autowired
    private SessaoService service;

    // USER

    @Operation(summary = "Lista sessões", description = "Realiza a listagem de todas sessões e seus filmes relacionados", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessaoResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar as sessões!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/listar-todas")
    public ResponseEntity<?> listarSessoes(){
        return ResponseEntity.ok(SessaoMapper.converterListaDeSessaoEntidadeParaListaDeSessaoResponseDTO(service.listarTodasSessoes()));
    }

    // ADMIN

    @Operation(summary = "Cadastro de uma nova sessão", description = "Cadastra uma nova sessão", method = "POST", responses = {
            @ApiResponse(description = "Cadastro realizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessaoResponseDTO.class))),
            @ApiResponse(description = "Houve um erro ao cadastrar uma nova sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/novo")
    public ResponseEntity<?> adicionarSessao(@RequestBody @Valid SessaoRequestDTO sessaoRequestDTO){
        return new ResponseEntity<SessaoResponseDTO>(SessaoMapper.converterCinemaEntidadeEmCinemaResponseDTO(service.salvarSessao(SessaoMapper.converterSessaoRequestDTOEmSessaoEntidade(sessaoRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta sessões", description = "Realiza a remoção de sessões pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível deletar a sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/deletar-sessao/{idSessao}")
    public ResponseEntity<?> deletarCinema(@PathVariable("idSessao") @Valid @NotNull(message = "Informe o id da sessão!") Long idSessao){
        service.deletarSessaoPorId(idSessao);
        return ResponseEntity.noContent().build();
    }


}
