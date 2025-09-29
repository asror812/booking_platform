package com.example.booking_platform.repository;

import com.example.booking_platform.model.HotelFacility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelFacilityRepository extends JpaRepository<HotelFacility, Integer> {

    Optional<HotelFacility> findByName(String name);
}
