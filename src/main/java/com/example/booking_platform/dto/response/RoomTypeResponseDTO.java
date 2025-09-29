package com.example.booking_platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomTypeResponseDTO {

    private UUID id;

    private String typeName;

    private Integer capacity;

    private Long pricePerDay;

    private Set<RoomFacilityResponseDTO> roomFacilities;

}
