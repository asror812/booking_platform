package com.example.booking_platform.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class HotelUpdateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String description;

}
