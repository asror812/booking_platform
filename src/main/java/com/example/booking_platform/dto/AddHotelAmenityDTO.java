package com.example.booking_platform.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddHotelAmenityDTO {

    @NotNull
    private Long hotelId;

    @NotNull
    private Integer amenityId;
}
