package com.example.booking_platform.hotel.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HotelCreateDTO {


    @NotBlank
    private String city;

    @NotBlank
    private String name;


    @NotBlank
    private String description;

    private boolean petsAllowed;

    @NotNull
    private MultipartFile picture;


}
