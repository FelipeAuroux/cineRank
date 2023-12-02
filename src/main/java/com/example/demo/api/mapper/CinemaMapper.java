package com.example.demo.api.mapper;

import com.example.demo.api.dto.cinema.CinemaRequestDTO;
import com.example.demo.api.dto.cinema.CinemaResponseDTO;
import com.example.demo.domain.model.Cinema;
import org.modelmapper.ModelMapper;

public class CinemaMapper {

    public static Cinema converterCinemaRequestDTOEmCinemaEntidade(CinemaRequestDTO cinemaRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cinemaRequestDTO, Cinema.class);
    }

    public static CinemaResponseDTO converterCinemaEntidadeEmCinemaResponseDTO(Cinema cinema) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cinema, CinemaResponseDTO.class);
    }
}
