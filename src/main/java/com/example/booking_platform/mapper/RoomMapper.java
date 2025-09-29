package com.example.booking_platform.mapper;

import org.mapstruct.Mapper;

import com.example.booking_platform.dto.response.RoomResponseDTO;
import com.example.booking_platform.model.Room;

@Mapper(componentModel = "spring", uses = {})
public interface RoomMapper {
    RoomResponseDTO toResponseDTO(Room room);
}
