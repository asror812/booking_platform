package com.example.booking_platform.mapper;

import org.mapstruct.Mapper;

import com.example.booking_platform.dto.response.HotelFacilityResponseDTO;
import com.example.booking_platform.model.HotelFacility;

@Mapper(componentModel = "spring")
public interface HotelFacilityMapper {
    HotelFacilityResponseDTO toResponseDTO(HotelFacility hotelFacility);
}
