package com.example.booking_platform.service;

import com.example.booking_platform.dto.response.HotelFacilityResponseDTO;
import com.example.booking_platform.mapper.HotelFacilityMapper;
import com.example.booking_platform.model.HotelFacility;
import com.example.booking_platform.repository.HotelFacilityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelFacilityService {

    private final HotelFacilityRepository repository;

    private final HotelFacilityMapper hotelFacilityMapper;

    public List<HotelFacilityResponseDTO> getAllFacilities() {
        List<HotelFacility> all = repository.findAll();

        return all.stream()
                .map(hotelFacility -> hotelFacilityMapper.toResponseDTO(hotelFacility))
                .collect(Collectors.toList());
    }
}
