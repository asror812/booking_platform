package com.example.booking_platform.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CitySummaryResponseDTO {
    private UUID id;

    private String name;

    private String code;

    private String imageUrl;

    private Long hotelCount;
}
