package com.example.booking_platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CityHotelSummaryDTO {

    private String name;

    private String imageUrl;

    private Long hotelCount;

}
