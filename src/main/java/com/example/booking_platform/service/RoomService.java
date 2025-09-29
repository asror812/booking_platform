package com.example.booking_platform.service;

import com.example.booking_platform.mapper.RoomMapper;
import com.example.booking_platform.mapper.RoomTypeMapper;
import com.example.booking_platform.dto.response.RoomResponseDTO;
import com.example.booking_platform.dto.response.RoomTypeResponseDTO;
import com.example.booking_platform.model.Room;
import com.example.booking_platform.repository.RoomRepository;
import com.example.booking_platform.model.RoomType;
import com.example.booking_platform.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    private final RoomTypeMapper roomTypeMapper;

    private final RoomMapper roomMapper;

    public List<RoomResponseDTO> getHotelRooms(Long id) {
        List<Room> rooms = roomRepository.findRoomsByHotel_Id(id);

        return rooms.stream()
                .map(room -> roomMapper.toResponseDTO(room))
                .toList();
    }

    public List<RoomResponseDTO> getAll() {

        return roomRepository.findAll()
                .stream().map(room -> roomMapper.toResponseDTO(room))
                .toList();
    }

    /*
     * public void create(RoomCreateDTO createDTO) {
     * Long hotelId = createDTO.getHotelId();
     * 
     * Hotel hotel = hotelRepository.findById(hotelId)
     * .orElseThrow(() -> new EntityNotFoundException("Hotel", hotelId.toString()));
     * 
     * val roomExists = roomRepository.findRoomsByHotel_Id(hotelId).stream()
     * .anyMatch(i -> Objects.equals(i.getNumber(), createDTO.getNumber()));
     * 
     * if (roomExists) {
     * throw new DuplicateEntityException(
     * "Room with number " + createDTO.getNumber() + " already exists in hotel "
     * + hotel);
     * }
     * 
     * RoomType roomType = roomTypeRepository.findById(createDTO.getRoomType())
     * .orElseThrow(() -> new EntityNotFoundException("RoomType",
     * createDTO.getRoomType().toString(0)));
     * 
     * Room newRoom = new Room();
     * newRoom.setNumber(createDTO.getNumber());
     * newRoom.setHotel(hotel);
     * newRoom.setRoomType(roomType);
     * 
     * /* hotel.getRooms().add(newRoom);
     * 
     * roomRepository.save(newRoom);
     * }
     */

    public List<RoomTypeResponseDTO> getAllRoomTypes() {

        List<RoomType> all = roomTypeRepository.findAll();

        return all.stream()
                .map(roomType -> roomTypeMapper.toResponseDTO(roomType))
                .toList();
    }
}
