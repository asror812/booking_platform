package com.example.booking_platform.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_platform.dto.response.HotelResponseDTO;
import com.example.booking_platform.dto.response.HotelSearchResponseDTO;
import com.example.booking_platform.model.Hotel;
import com.example.booking_platform.model.HotelFacility;
import com.example.booking_platform.model.HotelImage;

@Mapper(componentModel = "spring", uses = { RoomTypeMapper.class, HotelFacilityMapper.class, CityMapper.class })
public interface HotelMapper {

    @Mapping(target = "facilities", expression = "java(mapFacilities(hotel.getFacilities()))")
    @Mapping(target = "mainImageUrl", ignore = true)
    @Mapping(target = "price", ignore = true)
    HotelSearchResponseDTO toSearchResponseDTO(Hotel hotel, @Context Integer guestsCount);

    @AfterMapping
    default void enrich(@MappingTarget HotelSearchResponseDTO dto, Hotel hotel, @Context Integer guestsCount) {
        hotel.getImages().stream()
                .filter(HotelImage::isMain)
                .findFirst()
                .ifPresent(mainImage -> dto.setMainImageUrl(mainImage.getImageUrl()));

        Long minPrice = hotel.getRoomTypes().stream()
                .filter(rt -> rt.getCapacity() >= guestsCount)
                .map(rt -> rt.getPricePerDay())
                .min(Long::compareTo).get();

        dto.setPrice(minPrice);
    }

    default List<String> mapFacilities(Set<HotelFacility> facilities) {
        if (facilities == null) {
            return List.of();
        }

        return facilities.stream()
                .map(HotelFacility::getName)
                .toList();
    }

    HotelResponseDTO toResponseDTO(Hotel hotel);

}
