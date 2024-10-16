package com.example.booking_platform.room;


import com.example.booking_platform.room.dto.RoomCreateDTO;
import com.example.booking_platform.room.dto.RoomResponseDTO;
import com.example.booking_platform.room.dto.RoomTypeResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @PostMapping("/room")
    public String createRoom(@ModelAttribute @Valid RoomCreateDTO roomCreateDTO) {
       roomService.create(roomCreateDTO);
       return "redirect:/%s/update".formatted(roomCreateDTO.getHotelId());
   }


}
