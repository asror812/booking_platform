package com.example.booking_platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room_types")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    private Integer capacity;

    @Column(name = "price_per_day", nullable = false)
    private Long pricePerDay;

    @ManyToMany
    @JoinTable(name = "r_room_facilities", joinColumns = { @JoinColumn(name = "room_type_id") }, inverseJoinColumns = {
            @JoinColumn(name = "room_facility_id") })
    private Set<RoomFacility> roomFacilities;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;

    @OneToMany(mappedBy = "roomType")
    private List<Bed> beds;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}

// hotel facility, room facility, bed type are globally defined

// room type is it global or not ?
