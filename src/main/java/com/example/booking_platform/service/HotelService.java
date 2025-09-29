package com.example.booking_platform.service;

import com.example.booking_platform.exception.EntityNotFoundException;
import com.example.booking_platform.repository.CityRepository;

import com.example.booking_platform.dto.response.CityHotelSummaryDTO;
import com.example.booking_platform.dto.response.HotelResponseDTO;
import com.example.booking_platform.dto.response.HotelSearchResponseDTO;
import com.example.booking_platform.mapper.HotelMapper;
import com.example.booking_platform.model.Hotel;
import com.example.booking_platform.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;
    private final HotelRepository repository;
    private final CityRepository cityRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<CityHotelSummaryDTO> getHotelCountInUzbekistanCities() {
        List<CityHotelSummaryDTO> hotelCitiesInfo = mapObjectToCityHotelResponseDTO(
                cityRepository.getHotelCitiesInfo());

        return hotelCitiesInfo;
    }

    private List<CityHotelSummaryDTO> mapObjectToCityHotelResponseDTO(List<Object[]> objects) {

        return objects.stream()
                .map(obj -> new CityHotelSummaryDTO(
                        (String) obj[0],
                        (String) obj[1],
                        (Long) obj[2]))
                .toList();

    }

    public List<HotelSearchResponseDTO> search(UUID cityId, String checkIn, String checkOut, String guests) {
        int guestsCount = Integer.parseInt(guests.split(" ")[0]);
        LocalDate checkInDate = LocalDate.parse(checkIn, formatter);
        LocalDate checkOutDate = LocalDate.parse(checkOut, formatter);

        return repository.searchHotelsByCityAndGuestNumber(cityId, checkInDate, checkOutDate, guestsCount).stream()
                .map(hotel -> hotelMapper.toSearchResponseDTO(hotel, guestsCount))
                .toList();
    }

    public List<HotelResponseDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(hotel -> hotelMapper.toResponseDTO(hotel))
                .toList();
    }

    public HotelResponseDTO getHotel(Long id) {

        Optional<Hotel> existingHotel = repository.findById(id);

        if (existingHotel.isEmpty()) {
            throw new EntityNotFoundException("Hotel", id.toString());
        }

        return hotelMapper.toResponseDTO(existingHotel.get());
    }

}