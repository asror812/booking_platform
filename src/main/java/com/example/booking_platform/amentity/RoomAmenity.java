package com.example.booking_platform.amentity;


import com.example.booking_platform.room.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@Entity
public class RoomAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "additional_cost")
    private Long additionalCost;

    @ManyToMany(mappedBy = "amenities")
    private List<RoomType> roomTypes;

}