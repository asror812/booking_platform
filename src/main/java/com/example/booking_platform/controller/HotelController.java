package com.example.booking_platform.controller;

import com.example.booking_platform.service.CityService;
import com.example.booking_platform.service.HotelService;

import com.example.booking_platform.dto.response.CityHotelSummaryDTO;
import com.example.booking_platform.dto.response.CityResponseDTO;
import com.example.booking_platform.dto.response.HotelResponseDTO;
import com.example.booking_platform.dto.response.HotelSearchResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    private final CityService cityService;

    @GetMapping
    public String getCityDetailInfo(Model model) {
        List<CityHotelSummaryDTO> citySummaries = hotelService.getHotelCountInUzbekistanCities();
        model.addAttribute("summaries", citySummaries);

        List<CityResponseDTO> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        return "index";
    }

    @GetMapping("/results")
    public String searchHotels(@RequestParam UUID cityId,
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam int adults,
            @RequestParam int children,
            Model model) {

        List<CityResponseDTO> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        List<HotelSearchResponseDTO> results = hotelService.search(
                cityId, checkIn, checkOut, adults, children);

        if (results.isEmpty()) {
            model.addAttribute("noResults", true);
            model.addAttribute("message", "No hotels found matching your criteria.");
        }

        model.addAttribute("hotels", results);
        model.addAttribute("cityId", cityId);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        model.addAttribute("adults", adults);
        model.addAttribute("children", children);

        return "search-result";
    }

    @GetMapping("/hotels/{id}/see-rooms")
    public String getHotelAvailabilities(@PathVariable Long id, Model model) {
        HotelResponseDTO dto = hotelService.getById(id);
        model.addAttribute("hotel", dto);
        return "see-rooms";
    }
}