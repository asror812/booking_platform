package com.example.booking_platform.room;


import com.example.booking_platform.hotel.Hotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "room_type" , nullable = false)
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "hotel_id" , nullable = false)
    private Hotel hotel;




}
