package com.example.booking_platform.hotel;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel , Long> {

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.amenities")
    List<Hotel> getThreeHotels(Pageable pageable) ;


    @Query(value = "SELECT * FROM hotel h WHERE ()" , nativeQuery = true)
    List<Hotel> searchHotel();
}
