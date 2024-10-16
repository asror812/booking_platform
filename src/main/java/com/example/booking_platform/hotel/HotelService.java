package com.example.booking_platform.hotel;


import com.example.booking_platform.address.City;
import com.example.booking_platform.amentity.HotelAmenity;
import com.example.booking_platform.amentity.HotelAmenityRepository;
import com.example.booking_platform.exception.SpringMVCException;
import com.example.booking_platform.hotel.dto.*;
import com.example.booking_platform.room.RoomRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final HotelAmenityRepository hotelAmenityRepository;
    private final RoomRepository roomRepository;


    @Transactional
    public List<HotelResponseDTO> getAll() {
        return hotelRepository
                .findAll()
                .stream().map(h -> modelMapper.map(h , HotelResponseDTO.class))
                .toList();
    }


    @Transactional
    public void create(HotelCreateDTO dto) {
        MultipartFile file = dto.getPicture();

        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        Path  path = Path.of("src/main/resources/static/img/" + fileName);

        try {
            file.transferTo(path);
        }catch (IOException e) {
            throw new SpringMVCException(e.getMessage());
        }

        Hotel hotel = new Hotel(null , dto.getName(), City.valueOf(dto.getCity()) , Collections.emptyList() ,
                Collections.emptyList(), Collections.emptyList(), dto.getDescription() ,
                dto.isPetsAllowed() , fileName);
        hotel.setFileName(fileName);
        hotelRepository.save(hotel);
    }

    @Transactional
    public HotelResponseDTO getHotel(Long id) {

        Optional<Hotel> hotel = hotelRepository.findById(id);

        if (hotel.isEmpty()) {
             throw new SpringMVCException.EntityNotFoundException("Hotel", id.toString());
        }

        return modelMapper.map(hotel , HotelResponseDTO.class);
    }

    public Resource downloadFile(Long id ,  HttpServletResponse response) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new SpringMVCException.EntityNotFoundException("Hotel", id.toString()));

        response.addHeader("content-type" , "webapp");
        Path path = Path.of("/src/main/resources/static/images" + id);

        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Long id , HotelUpdateDTO updateDTO) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(
                        () -> new SpringMVCException.EntityNotFoundException("Hotel", id.toString()));

        hotel.setName(updateDTO.getName());
        hotel.setCity(City.valueOf(updateDTO.getCity()));
        hotel.setDescription(updateDTO.getDescription());

        hotelRepository.save(hotel);
    }

    public void addAmenity(AddHotelAmenityDTO dto) {
        Integer amenityId = dto.getAmenityId();
        Long hotelId = dto.getHotelId();

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new SpringMVCException.EntityNotFoundException("Hotel", hotelId.toString()));

        HotelAmenity amenity = hotelAmenityRepository.findById(amenityId)
                .orElseThrow(() ->
                        new SpringMVCException.EntityNotFoundException("Amenity" , amenityId.toString()));


        hotel.getAmenities().add(amenity);
        amenity.getHotels().add(hotel);
        hotelAmenityRepository.save(amenity);
        hotelRepository.save(hotel);

    }


    public List<HotelAmenityResponseDTO> getOther(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new SpringMVCException.EntityNotFoundException("Hotel", id.toString()));

        List<HotelAmenity> amenities = hotelAmenityRepository.findAll();

        amenities.removeIf(amenity -> hotel.getAmenities().contains(amenity));

        return amenities.stream().map(a -> modelMapper.map(a, HotelAmenityResponseDTO.class))
                .toList();
    }

    public List<HotelResponseDTO> searchHotels(HotelSearchForUserDTO dto) {

        System.out.print("Hello world!");
          List<Hotel> hotels = hotelRepository.searchHotel(dto.getId(), dto.getCity() , dto.getName() , dto.getPetsAllowed());

          return hotels.stream().map(h -> modelMapper.map(h , HotelResponseDTO.class))
                  .toList();
    }

    public void removeAmenity(Long hotelId , Integer amenityId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(
                        () -> new SpringMVCException.EntityNotFoundException("Hotel", hotelId.toString()));


        HotelAmenity amenity = hotelAmenityRepository.findById(amenityId)
                .orElseThrow(() -> new SpringMVCException.EntityNotFoundException("Amenity", amenityId.toString()));

        hotel.getAmenities().removeIf(a -> a.getId().equals(amenityId));
        amenity.getHotels().removeIf(h -> h.getId().equals(hotelId));

        hotelAmenityRepository.save(amenity);
        hotelRepository.save(hotel);
    }

    public void deleteRoom(Long hotelId, UUID roomId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(
                        () -> new SpringMVCException.EntityNotFoundException("Hotel", hotelId.toString()));

        hotel.getRooms().removeIf(r -> r.getId().equals(roomId));

        hotelRepository.save(hotel);
    }
}
