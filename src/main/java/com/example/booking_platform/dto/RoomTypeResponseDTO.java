package com.example.booking_platform.dto;


import com.example.booking_platform.model.RoomAmenity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomTypeResponseDTO {

    private Integer id;

    @NotBlank
    private String typeName;

    @NotBlank
    private String description;

    @NotNull
    private Integer capacity;

    @NotNull
    private Long pricePerDay;


    private List<RoomAmenity> amenities;

}
