package com.example.booking_platform.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Entity
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "room_type_id" )
    private RoomType roomType;
}
