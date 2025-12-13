package com.example.booking_platform.service;

import com.example.booking_platform.exception.EntityNotFoundException;
import com.example.booking_platform.exception.InvalidGuestCountException;
import com.example.booking_platform.repository.CityRepository;
import com.example.booking_platform.dto.response.CityHotelSummaryDTO;
import com.example.booking_platform.dto.response.HotelResponseDTO;
import com.example.booking_platform.dto.response.HotelSearchResponseDTO;
import com.example.booking_platform.mapper.HotelMapper;
import com.example.booking_platform.model.Hotel;
import com.example.booking_platform.repository.HotelRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;
    private final HotelRepository repository;
    private final CityRepository cityRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final int MAX_GUESTS_PER_ROOM = 10;

    public List<CityHotelSummaryDTO> getHotelCountInUzbekistanCities() {
        return mapObjectToCityHotelResponseDTO(cityRepository.getHotelCitiesInfo());
    }

    private List<CityHotelSummaryDTO> mapObjectToCityHotelResponseDTO(List<Object[]> objects) {
        return objects.stream()
                .map(obj -> new CityHotelSummaryDTO(
                        (String) obj[0],
                        (String) obj[1],
                        (Long) obj[2]))
                .toList();
    }

    public List<HotelSearchResponseDTO> search(UUID cityId, String checkIn, String checkOut, int adults, int children) {
        GuestCount guestCount = validateGuestCount(adults, children);

        LocalDate checkInDate = LocalDate.parse(checkIn, formatter);
        LocalDate checkOutDate = LocalDate.parse(checkOut, formatter);

        validateDates(checkInDate, checkOutDate);

        int totalCapacity = guestCount.calculateTotalCapacity();

        log.info("Searching hotels for city: {}, adults: {}, children: {}, total capacity: {}",
                cityId, guestCount.adults(), guestCount.children(), totalCapacity);

        return repository.searchHotelsByCityAndGuestNumber(cityId, checkInDate, checkOutDate, totalCapacity)
                .stream()
                .map(hotel -> hotelMapper.toSearchResponseDTO(hotel, totalCapacity))
                .toList();
    }

    private GuestCount validateGuestCount(int adults, int children) {
        if (adults < 1) {
            throw new InvalidGuestCountException("At least 1 adult is required for booking");
        }

        if (adults < 0 || children < 0) {
            throw new InvalidGuestCountException("Guest counts cannot be negative");
        }

        int totalGuests = adults + children;
        if (totalGuests > MAX_GUESTS_PER_ROOM) {
            throw new InvalidGuestCountException(
                    String.format("Total guests (%d) exceeds maximum allowed (%d)",
                            totalGuests, MAX_GUESTS_PER_ROOM));
        }

        if (children > 0 && adults == 0) throw new InvalidGuestCountException("Children cannot stay without adults");
        

        return new GuestCount(adults, children);
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate today = LocalDate.now();

        if (checkIn.isBefore(today)) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }

        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
    }

    public List<HotelResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    public HotelResponseDTO getById(@NonNull Long id) {
        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel", id.toString()));
        return hotelMapper.toResponseDTO(hotel);
    }

    private record GuestCount(int adults, int children) {
        int calculateTotalCapacity() {
            return adults + (int) Math.ceil(children / 2.0);
        }
    }
}