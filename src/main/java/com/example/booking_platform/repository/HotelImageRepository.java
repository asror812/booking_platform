package com.example.booking_platform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_platform.model.HotelImage;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, UUID> {

}
