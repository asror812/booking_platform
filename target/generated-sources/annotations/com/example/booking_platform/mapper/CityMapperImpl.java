package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.CityResponseDTO;
import com.example.booking_platform.model.City;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T01:16:17+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityResponseDTO toResponseDTO(City city) {
        if ( city == null ) {
            return null;
        }

        CityResponseDTO cityResponseDTO = new CityResponseDTO();

        cityResponseDTO.setId( city.getId() );
        cityResponseDTO.setName( city.getName() );
        cityResponseDTO.setCode( city.getCode() );
        cityResponseDTO.setImageUrl( city.getImageUrl() );

        return cityResponseDTO;
    }
}
