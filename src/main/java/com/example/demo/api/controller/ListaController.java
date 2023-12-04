package com.example.demo.api.controller;

import com.example.demo.api.dto.lista.ListaRequestDTO;
import com.example.demo.api.dto.lista.ListaResponseDTO;
import com.example.demo.api.mapper.ListaMapper;
import com.example.demo.domain.service.ListaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @PostMapping("/novo")
    public ResponseEntity<?> salvarLista(@RequestBody @Valid ListaRequestDTO listaRequestDTO){
        return new ResponseEntity<ListaResponseDTO>(ListaMapper.converterListaEntidadeEmListaResponseDTO( service.salvarLista(ListaMapper.converterListaRequestDTOEmListaEntidade(listaRequestDTO))), HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<?> listarTodasListas(){
        return ResponseEntity.ok(ListaMapper.converterListaDeListaEntidadeParaListaDeListaResponseDTO(service.listarTodasListas()));
    }

    // ADMIN


}
