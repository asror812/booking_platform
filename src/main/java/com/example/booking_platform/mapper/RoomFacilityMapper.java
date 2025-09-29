package com.example.booking_platform.mapper;

import org.mapstruct.Mapper;

import com.example.booking_platform.dto.response.RoomFacilityResponseDTO;
import com.example.booking_platform.model.RoomFacility;

@Mapper(componentModel = "spring")
public interface RoomFacilityMapper {
    RoomFacilityResponseDTO toResponseDTO(RoomFacility roomFacility);
}
