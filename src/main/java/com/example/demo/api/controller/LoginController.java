package com.example.demo.api.controller;


import com.example.demo.api.dto.login.LoginRequestDTO;
import com.example.demo.api.dto.login.LoginResponseDTO;
import com.example.demo.api.mapper.LoginMapper;
import com.example.demo.api.message.MensagemResponse;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.service.UsuarioService;
import com.example.demo.utils.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/login")
@Tag(name = "Login")
public class LoginController {

    @Autowired
    private UsuarioService service;

    @Autowired
    public AuthenticationManager authenticationManager;
    private final int MAX_ATTEMPTS = 3;

    @Operation(summary = "Realizar login", description = "Realiza login com nome de usuário e senha", method = "POST", responses = {
            @ApiResponse(description = "Login realizado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(description = "Login inválido!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/logar")
    public ResponseEntity<?> enter(@RequestBody @Valid LoginRequestDTO loginRequestDTO, HttpServletRequest request) {
        MensagemResponse message = new MensagemResponse();

        // creates a log of the login request
        Log.createSimpleLog(loginRequestDTO, request);

        if (service.buscarUsuarioPorNomeDeUsuario(loginRequestDTO.getUsuario()) != null) {

            if (service.tentativasDeLoginDoUsuario(loginRequestDTO.getUsuario()) < MAX_ATTEMPTS) {
                return processLogin(loginRequestDTO);
            } else {
                // At this point, we know that the allowed number of attempts has been exceeded
                // check if there is a waiting date for a new login attempt
                if (service.verificarDataDeLiberacaoLogin(loginRequestDTO.getUsuario())) {

                    // there is a lockout date for login
                    Date releaseDate = service.obterDataDeLiberacaoLogin(loginRequestDTO.getUsuario());

                    // check if it is still valid
                    if (releaseDate.after(new Date(System.currentTimeMillis()))) {
                        // if it is not expired yet
                        message.setMensagem("Sua nova tentativa apenas poderá ser realizada em " + releaseDate);
                        return new ResponseEntity<MensagemResponse>(message, HttpStatus.LOCKED);
                    } else {
                        // time expired
                        service.redefinirTentativasELiberarLogin(loginRequestDTO.getUsuario());
                        return processLogin(loginRequestDTO);
                    }
                } else {
                    // If it doesn't exist, add the waiting time for a new login attempt for this user
                    Date releaseDate = service.liberarLogin(loginRequestDTO.getUsuario());
                    message.setMensagem("Tentativas esgotadas, o acesso será liberado em " + releaseDate);
                    return new ResponseEntity<MensagemResponse>(message, HttpStatus.LOCKED);
                }
            }
        }
        message.setMensagem("Login inválido!");
        return new ResponseEntity<MensagemResponse>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity<?> processLogin(LoginRequestDTO loginRequestDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsuario(), loginRequestDTO.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            // check login
            if (auth.isAuthenticated()) {
                // register login
                Usuario usuarioLogado = service.login((Usuario) auth.getPrincipal());

                return new ResponseEntity<LoginResponseDTO>(LoginMapper.mapperUsuarioToLoginResponseDTO(usuarioLogado), HttpStatus.ACCEPTED);
            }
        } catch (AuthenticationException e) {
            // increment attempts
            service.updateAttempts(loginRequestDTO.getUsuario());
        }

        return new ResponseEntity<MensagemResponse>(new MensagemResponse("Login inválido!"), HttpStatus.NOT_ACCEPTABLE);
    }


}
