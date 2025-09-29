package com.example.booking_platform.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CityResponseDTO {
    private UUID id;

    private String name;

    private String code;

    private String imageUrl;
}
