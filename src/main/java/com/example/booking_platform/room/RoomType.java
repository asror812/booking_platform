package com.example.booking_platform.room;


import com.example.booking_platform.amentity.RoomAmenity;
import com.example.booking_platform.hotel.Hotel;
import com.example.booking_platform.room.bed.Bed;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type_name")
    @NotBlank
    private String typeName;

    @NotBlank
    private String description;

    @NotNull
    private Integer capacity;

    @NotNull
    @Column(name = "price_per_day")
    private Long pricePerDay;

    @ManyToMany
    @JoinTable(name = "room_amenities" ,
               joinColumns = {@JoinColumn(name = "room_type_id")},
               inverseJoinColumns = {@JoinColumn(name = "amenity_id")})
    private List<RoomAmenity> amenities;

    @OneToMany(mappedBy = "roomType")
    private List<Bed> beds ;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
