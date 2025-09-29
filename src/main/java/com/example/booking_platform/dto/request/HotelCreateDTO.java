package com.example.booking_platform.dto.request;


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

    @NotNull
    private MultipartFile picture;


}
