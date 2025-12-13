package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.RoomFacilityResponseDTO;
import com.example.booking_platform.dto.response.RoomTypeResponseDTO;
import com.example.booking_platform.model.RoomFacility;
import com.example.booking_platform.model.RoomType;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-13T22:53:19+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class RoomTypeMapperImpl implements RoomTypeMapper {

    @Autowired
    private RoomFacilityMapper roomFacilityMapper;

    @Override
    public RoomTypeResponseDTO toResponseDTO(RoomType roomType) {
        if ( roomType == null ) {
            return null;
        }

        RoomTypeResponseDTO roomTypeResponseDTO = new RoomTypeResponseDTO();

        roomTypeResponseDTO.setCapacity( roomType.getCapacity() );
        roomTypeResponseDTO.setId( roomType.getId() );
        roomTypeResponseDTO.setPricePerDay( roomType.getPricePerDay() );
        roomTypeResponseDTO.setRoomFacilities( roomFacilitySetToRoomFacilityResponseDTOSet( roomType.getRoomFacilities() ) );
        roomTypeResponseDTO.setTypeName( roomType.getTypeName() );

        return roomTypeResponseDTO;
    }

    protected Set<RoomFacilityResponseDTO> roomFacilitySetToRoomFacilityResponseDTOSet(Set<RoomFacility> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoomFacilityResponseDTO> set1 = new LinkedHashSet<RoomFacilityResponseDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoomFacility roomFacility : set ) {
            set1.add( roomFacilityMapper.toResponseDTO( roomFacility ) );
        }

        return set1;
    }
}
