package com.example.booking_platform.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomTypeSearchResponseDTO {

    private String typeName;

    private Long pricePerDay;
    private Set<String> roomFacilities;
}
