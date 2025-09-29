package com.example.booking_platform.repository;

import com.example.booking_platform.model.Bed;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, UUID> {
}
