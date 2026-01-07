package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.RoomResponseDTO;
import com.example.booking_platform.model.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T01:17:19+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
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
