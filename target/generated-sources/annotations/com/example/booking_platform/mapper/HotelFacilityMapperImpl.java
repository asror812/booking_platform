package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.HotelFacilityResponseDTO;
import com.example.booking_platform.model.HotelFacility;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T01:17:36+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class HotelFacilityMapperImpl implements HotelFacilityMapper {

    @Override
    public HotelFacilityResponseDTO toResponseDTO(HotelFacility hotelFacility) {
        if ( hotelFacility == null ) {
            return null;
        }

        HotelFacilityResponseDTO hotelFacilityResponseDTO = new HotelFacilityResponseDTO();

        hotelFacilityResponseDTO.setName( hotelFacility.getName() );
        hotelFacilityResponseDTO.setIconUrl( hotelFacility.getIconUrl() );

        return hotelFacilityResponseDTO;
    }
}
