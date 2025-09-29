package com.example.booking_platform.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelSearchRequestDTO {

    @NotNull
    private UUID cityId;

    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to;

}
