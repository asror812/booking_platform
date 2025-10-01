package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.RoomFacilityResponseDTO;
import com.example.booking_platform.model.RoomFacility;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T10:01:01+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RoomFacilityMapperImpl implements RoomFacilityMapper {

    @Override
    public RoomFacilityResponseDTO toResponseDTO(RoomFacility roomFacility) {
        if ( roomFacility == null ) {
            return null;
        }

        RoomFacilityResponseDTO roomFacilityResponseDTO = new RoomFacilityResponseDTO();

        roomFacilityResponseDTO.setId( roomFacility.getId() );
        roomFacilityResponseDTO.setName( roomFacility.getName() );

        return roomFacilityResponseDTO;
    }
}
