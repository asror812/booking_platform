package com.example.booking_platform.controller;

import com.example.booking_platform.dto.response.RoomResponseDTO;
import com.example.booking_platform.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    public final RoomService roomService;

    @GetMapping("/rooms")
    public List<RoomResponseDTO> getAllRooms() {
        return roomService.getAll();
    }

    /* @PostMapping("/room")
    public String createRoom(@ModelAttribute @Valid RoomCreateDTO roomCreateDTO) {
        roomService.create(roomCreateDTO);
        return "redirect:/%s/update".formatted(roomCreateDTO.getHotelId());
    } */

}
