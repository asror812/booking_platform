package com.example.booking_platform.hotel;


import com.example.booking_platform.address.City;
import com.example.booking_platform.amentity.HotelAmenity;
import com.example.booking_platform.room.Room;
import com.example.booking_platform.room.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

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
    private boolean petsAllowed;

    private String fileName;

}
