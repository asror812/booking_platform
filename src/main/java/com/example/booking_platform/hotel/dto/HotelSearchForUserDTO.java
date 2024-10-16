package com.example.booking_platform.hotel.dto;

import com.example.booking_platform.address.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelSearchForUserDTO {

    private Long id;

    private String name;

    private City city;

    private Boolean petsAllowed;

}
