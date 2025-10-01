package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.RoomResponseDTO;
import com.example.booking_platform.model.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T10:01:01+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomResponseDTO toResponseDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setId( room.getId() );
        roomResponseDTO.setNumber( room.getNumber() );

        return roomResponseDTO;
    }
}
