package com.example.booking_platform.mapper;

import org.mapstruct.Mapper;

import com.example.booking_platform.dto.response.RoomTypeResponseDTO;
import com.example.booking_platform.model.RoomType;

@Mapper(componentModel = "spring", uses = { RoomFacilityMapper.class })
public interface RoomTypeMapper {

    RoomTypeResponseDTO toResponseDTO(RoomType roomType);

}
