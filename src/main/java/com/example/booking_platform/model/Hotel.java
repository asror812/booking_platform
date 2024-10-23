package com.example.booking_platform.model;


import com.example.booking_platform.enums.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private City city;

    @OneToMany(mappedBy = "hotel" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.LAZY)
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<RoomType> roomTypes;


    @ManyToMany
    @JoinTable(name = "hotel_amenities" ,
                joinColumns = {@JoinColumn(name = "hotel_id")},
                inverseJoinColumns = {@JoinColumn(name = "amenity_id")})
    private List<HotelAmenity> amenities;

    @Column(columnDefinition = "text")
    @NotBlank
    private String description;

    @Column(name = "pets_allowed")
    private Boolean petsAllowed;

    private String fileName;

}
