package com.example.booking_platform.amentity;

import com.example.booking_platform.hotel.dto.HotelAmenityResponseDTO;
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
