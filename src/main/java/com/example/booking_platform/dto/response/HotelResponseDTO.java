package com.example.booking_platform.dto.response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelResponseDTO {
    private Long id;

    private String name;

    private CityResponseDTO city;

    private List<RoomTypeResponseDTO> roomTypes = new ArrayList<>();

    private Set<HotelFacilityResponseDTO> facilities = new HashSet<>();

    private String description;

    private List<HotelImageResponseDTO> images = new ArrayList<>();
}
