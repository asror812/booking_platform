package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.HotelFacilityResponseDTO;
import com.example.booking_platform.model.HotelFacility;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-28T16:17:28+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
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

        return hotelFacilityResponseDTO;
    }
}
