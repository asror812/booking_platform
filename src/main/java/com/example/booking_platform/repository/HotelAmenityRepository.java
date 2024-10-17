package com.example.booking_platform.repository;


import com.example.booking_platform.model.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Integer> {


    Optional<HotelAmenity> findByName(String name);
}
