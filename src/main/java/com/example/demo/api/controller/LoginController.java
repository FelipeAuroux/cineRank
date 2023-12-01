package com.example.demo.api.controller;

import com.example.demo.api.dto.login.LoginRequestDTO;
import com.example.demo.api.dto.login.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
@Tag(name = "Login")
public class LoginController {

    @Operation(summary = "Realizar login", description = "Realiza login com nome de usuário e senha", method = "POST", responses = {
            @ApiResponse(description = "Login realizado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(description = "Login inválido!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/logar")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {

        return ResponseEntity.ok("Funcionando");
    }
}
