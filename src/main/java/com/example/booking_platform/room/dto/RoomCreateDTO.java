package com.example.booking_platform.room.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class RoomCreateDTO {

    @NotNull
    private Integer number;

    @NotNull
    private Integer roomType;

    @NotNull
    private Long hotelId;
}
