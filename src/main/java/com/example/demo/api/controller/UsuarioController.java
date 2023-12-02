package com.example.demo.api.controller;

import com.example.demo.api.dto.usuario.UsuarioComIDRequestDTO;
import com.example.demo.api.dto.usuario.UsuarioRequestDTO;
import com.example.demo.api.dto.usuario.UsuarioResponseDTO;
import com.example.demo.api.mapper.UsuarioMapper;
import com.example.demo.domain.service.UsuarioService;
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
@RequestMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // USER
    @Operation(summary = "Cadastra usuários", description = "Realiza cadastros de novos usuários", method = "POST", responses = {
            @ApiResponse(description = "Usuário cadastrado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(description = "Erro ao cadastrar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/novo")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.criarNovoUsuario(UsuarioMapper.converterUsuarioRequestDTOEmUsuarioEntidade(usuarioRequestDTO)));
        return new ResponseEntity<UsuarioResponseDTO>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    // ADMIN

    @Operation(summary = "Atualiza usuários", description = "Realiza a atualização de um usuário", method = "PUT", responses = {
            @ApiResponse(description = "Usuário atualizado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(description = "Erro ao atualizar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarUsuario(@RequestBody @Valid UsuarioComIDRequestDTO usuarioComIDRequestDTO) {
        return ResponseEntity.ok(UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.atualizarUsuarioPorId(UsuarioMapper.converterUsuarioComIDRequestDTOEmUsuarioEntidade(usuarioComIDRequestDTO))));
    }

    @Operation(summary = "Deleta usuários", description = "Realiza a remoção de um usuário por id", method = "DELETE", responses = {
            @ApiResponse(description = "Usuário deletado com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao deletar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/deletar/{idUsuario}")
    public ResponseEntity<?> deletarUsuarioPorId(@PathVariable("idUsuario") @Valid @NotNull(message = "Informe o id do usuário!") Long idUsuario) {
        service.deletarUsuarioPorId(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários", description = "Realiza a busca de um usuário por id", method = "GET", responses = {
            @ApiResponse(description = "Usuário encontrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(description = "Erro ao encontrar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable("idUsuario") @Valid @NotNull(message = "Informe o id do usuário!") Long idUsuario) {
        return ResponseEntity.ok(UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.buscarUsuarioPorId(idUsuario)));
    }

    @Operation(summary = "Lista usuários", description = "Realiza a listagem de todos os usuários cadastrados", method = "GET", responses = {
            @ApiResponse(description = "Usuários listados com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(description = "Erro ao listar usuários!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("listar-todos")
    public ResponseEntity<?> listarTodosUsuarios() {
        return ResponseEntity.ok(UsuarioMapper.converterListaDeUsuariosEntidadeParaListaDeUsuarioResponseDTO(service.listarTodosUsuarios()));
    }

}
