package com.example.booking_platform.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelFacilityResponseDTO {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
