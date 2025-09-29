package com.example.booking_platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.booking_platform.dto.response.CityResponseDTO;
import com.example.booking_platform.mapper.CityMapper;
import com.example.booking_platform.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<CityResponseDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(city -> cityMapper.toResponseDTO(city))
                .toList();
    }

}
