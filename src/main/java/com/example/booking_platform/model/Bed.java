package com.example.booking_platform.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "beds")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String type;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    public Bed(String type, Integer count, RoomType roomType) {
        this.type = type;
        this.count = count;
        this.roomType = roomType;
    }
}
