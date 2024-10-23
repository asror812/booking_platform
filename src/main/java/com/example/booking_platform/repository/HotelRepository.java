package com.example.booking_platform.repository;


import com.example.booking_platform.enums.City;
import com.example.booking_platform.model.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.amenities")
    List<Hotel> getThreeHotels(Pageable pageable) ;


        @Query(value = "SELECT * FROM public.hotel h " +
               "WHERE (:id IS NULL OR h.id = :id) " +
                "AND (:city IS NULL OR h.city LIKE :city + '%') " +
                "AND (:name IS NULL OR h.name LIKE :name + '%')" +
                "AND (:petsAllowed IS NULL OR h.pets_allowed = :petsAllowed)" ,
                nativeQuery = true)
        List<Hotel> searchHotel(@Param("id") Long id , @Param("city") String city ,
                                @Param("name") String name ,@Param("petsAllowed") Boolean petsAllowed);
}
