package com.example.booking_platform.controller;


import com.example.booking_platform.service.HotelService;
import com.example.booking_platform.dto.HotelResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final HotelService hotelService;

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        List<HotelResponseDTO> all = hotelService.getAll();
        model.addAttribute("hotels", all);

        return "admin/index";
    }
}
