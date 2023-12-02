package com.example.demo.api.controller;

import com.example.demo.api.dto.cinema.CinemaRequestDTO;
import com.example.demo.api.dto.cinema.CinemaResponseDTO;
import com.example.demo.api.mapper.CinemaMapper;
import com.example.demo.domain.service.CinemaService;
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

    // ADMIN

    @PostMapping("/novo")
    public ResponseEntity<?> adicionarCinema(@RequestBody @Valid CinemaRequestDTO cinemaRequestDTO){
        return new ResponseEntity<CinemaResponseDTO>(CinemaMapper.converterCinemaEntidadeEmCinemaResponseDTO(service.salvarCinema(CinemaMapper.converterCinemaRequestDTOEmCinemaEntidade(cinemaRequestDTO))), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar-cinema/{idCinema}")
    public ResponseEntity<?> deletarCinema(@PathVariable("idCinema") @Valid @NotNull(message = "Informe o id do cinema!") Long idCinema){
        service.deletarCinemaPorId(idCinema);
        return ResponseEntity.noContent().build();
    }


}
