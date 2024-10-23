package com.example.booking_platform.dto;


import com.example.booking_platform.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelSearchForAdminDTO {

    private Long id;

    private String name;

    private City city;

    private boolean petsAllowed;

}
