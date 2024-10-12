package com.example.booking_platform.room.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomResponseDTO {

    @NotNull
    private UUID id;

    @NotNull
    private Integer number;

    @NotNull
    private RoomTypeResponseDTO roomType;


}
