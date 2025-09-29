package com.example.booking_platform.mapper;

import org.mapstruct.Mapper;

import com.example.booking_platform.dto.response.CityResponseDTO;
import com.example.booking_platform.model.City;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponseDTO toResponseDTO(City city);
}
