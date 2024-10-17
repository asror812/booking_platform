package com.example.booking_platform.repository;


import com.example.booking_platform.address.City;
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


        @Query(value = "SELECT * FROM hotel h " +
               "WHERE (:id is null OR h.id = :id) " +
                "AND (:city is null OR h.city = :city) " +
                "AND (:name is null OR h.name = :name)" +
                "AND (:petsAllowed is null OR h.pets_allowed = :petsAllowed)" ,
                nativeQuery = true)
        List<Hotel> searchHotel(@Param("id") Long id , @Param("city") City city ,
                                @Param("name") String name ,@Param("petsAllowed") Boolean petsAllowed);
}
