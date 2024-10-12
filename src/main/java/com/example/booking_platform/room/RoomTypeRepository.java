package com.example.booking_platform.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository  extends JpaRepository<RoomType, Integer> {
}
