package com.example.booking_platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotel_facilities")
@ToString
public class HotelFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "icon_url")
    private String iconUrl;

    public HotelFacility(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }

}
