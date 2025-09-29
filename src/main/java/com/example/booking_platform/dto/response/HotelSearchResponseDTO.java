package com.example.booking_platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HotelSearchResponseDTO {
    private Long id;

    private String name;

    private String description;

    private List<String> facilities;

    private String mainImageUrl;

    private Long price;
}
