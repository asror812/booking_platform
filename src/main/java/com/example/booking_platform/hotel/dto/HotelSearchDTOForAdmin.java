package com.example.booking_platform.hotel.dto;

import com.example.booking_platform.address.City;
import com.example.booking_platform.amentity.HotelAmenity;
import com.example.booking_platform.room.Room;
import com.example.booking_platform.room.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelSearchDTOForAdmin {

    private Long id;

    private String name;

    private City city;


    @Column(name = "pets_allowed")
    private boolean petsAllowed;

}
