package com.example.booking_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_platform.model.RoomFacility;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Integer> {

}
