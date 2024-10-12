package com.example.booking_platform.hotel.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class HotelAmenityResponseDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;


    private boolean isFree;

    @NotNull
    private Long additionalCost;
}
