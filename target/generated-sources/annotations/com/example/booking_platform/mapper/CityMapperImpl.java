package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.CityResponseDTO;
import com.example.booking_platform.model.City;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-13T22:53:19+0500",
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

        cityResponseDTO.setCode( city.getCode() );
        cityResponseDTO.setId( city.getId() );
        cityResponseDTO.setImageUrl( city.getImageUrl() );
        cityResponseDTO.setName( city.getName() );

        return cityResponseDTO;
    }
}
