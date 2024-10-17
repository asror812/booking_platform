package com.example.booking_platform.service;


import com.example.booking_platform.exception.SpringMVCException;
import com.example.booking_platform.model.Hotel;
import com.example.booking_platform.repository.HotelRepository;
import com.example.booking_platform.dto.RoomCreateDTO;
import com.example.booking_platform.dto.RoomResponseDTO;
import com.example.booking_platform.dto.RoomTypeResponseDTO;
import com.example.booking_platform.model.Room;
import com.example.booking_platform.repository.RoomRepository;
import com.example.booking_platform.model.RoomType;
import com.example.booking_platform.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;

    public List<RoomResponseDTO> getHotelRooms(Long id){
        List<Room> rooms = roomRepository.findRoomsByHotel_Id(id);


        return rooms
                .stream().map(r -> modelMapper.map(r  , RoomResponseDTO.class))
                .toList();
    }

    public List<RoomResponseDTO> getAll() {

    return roomRepository.findAll()
            .stream().map(r -> modelMapper.map(r , RoomResponseDTO.class))
            .toList();
    }

    public void create(RoomCreateDTO createDTO) {
        Long hotelId = createDTO.getHotelId();

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new SpringMVCException.EntityNotFoundException("Hotel", hotelId.toString()));

        val roomExists = roomRepository.findRoomsByHotel_Id(hotelId).stream().anyMatch(i -> Objects.equals(i.getNumber(), createDTO.getNumber()));

        if (roomExists) {
            throw new SpringMVCException.DuplicateEntityException("Room with number " + createDTO.getNumber() + " already exists in hotel " + hotel);
        }

        val roomType = roomTypeRepository.findById(createDTO.getRoomType())
                .orElseThrow(() -> new SpringMVCException.EntityNotFoundException("RoomType", createDTO.getRoomType().toString()));

        Room newRoom = new Room();
        newRoom.setNumber(createDTO.getNumber());
        newRoom.setHotel(hotel);
        newRoom.setRoomType(roomType);

        hotel.getRooms().add(newRoom);

        roomRepository.save(newRoom);
    }

    public List<RoomTypeResponseDTO> getAllRoomTypes() {

        List<RoomType> all = roomTypeRepository.findAll();

        return all.stream()
                .map(r -> modelMapper.map(r, RoomTypeResponseDTO.class))
                .toList();
    }
}
