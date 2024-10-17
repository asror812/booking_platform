package com.example.booking_platform.service;

import com.example.booking_platform.dto.HotelAmenityResponseDTO;
import com.example.booking_platform.model.HotelAmenity;
import com.example.booking_platform.repository.HotelAmenityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelAmenityService {

    private final HotelAmenityRepository repository;
    private final ModelMapper modelMapper;

    public List<HotelAmenityResponseDTO> getAllAmenities() {
        List<HotelAmenity> all = repository.findAll();


        return all.stream()
                .map(a -> modelMapper.map(a  , HotelAmenityResponseDTO.class))
                .collect(Collectors.toList());
    }
}
