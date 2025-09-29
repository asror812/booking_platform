package com.example.booking_platform.repository;

import com.example.booking_platform.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.images WHERE h.id = :id")
    Optional<Hotel> findByIdWithImages(@Param("id") Long id);

    @Query("""
            SELECT DISTINCT h
            FROM Hotel h
            LEFT JOIN FETCH h.images
            LEFT JOIN FETCH h.facilities
            """)
    List<Hotel> findAllWithImagesAndFacilities();

    @Query("""
                SELECT DISTINCT h
                FROM Hotel h
                JOIN h.roomTypes rt
                JOIN rt.rooms r
                WHERE h.city.id = :cityId
                AND rt.capacity >= :guestsCount
                AND NOT EXISTS (
                    SELECT 1
                    FROM Reservation res
                    WHERE res.room = r
                    AND NOT (res.checkOut <= :checkIn OR res.checkIn >= :checkOut)
                )
            """)
    List<Hotel> searchHotelsByCityAndGuestNumber(UUID cityId, LocalDate checkIn, LocalDate checkOut,
            Integer guestsCount);
}
