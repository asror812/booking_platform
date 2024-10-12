package com.example.booking_platform.hotel.dto;


import com.example.booking_platform.address.City;
import com.example.booking_platform.room.dto.RoomResponseDTO;
import com.example.booking_platform.room.dto.RoomTypeResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelResponseDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private City city;

    @NotBlank
    private String description;

    @NotBlank
    private String fileName;

    private List<RoomResponseDTO> rooms;

    private List<HotelAmenityResponseDTO> amenities;


    private List<RoomTypeResponseDTO> roomTypes;
}
