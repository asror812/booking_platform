package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.RoomFacilityResponseDTO;
import com.example.booking_platform.model.RoomFacility;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-13T22:53:19+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
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
