package com.example.booking_platform.amentity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Integer> {


    Optional<HotelAmenity> findByName(String name);
}
