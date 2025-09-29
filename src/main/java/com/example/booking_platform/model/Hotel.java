package com.example.booking_platform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hotels")
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Builder.Default
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomType> roomTypes = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "h_hotel_facilities", joinColumns = { @JoinColumn(name = "hotel_id") }, inverseJoinColumns = {
            @JoinColumn(name = "facility_id") })
    private Set<HotelFacility> facilities = new HashSet<>();

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelImage> images = new ArrayList<>();

}
