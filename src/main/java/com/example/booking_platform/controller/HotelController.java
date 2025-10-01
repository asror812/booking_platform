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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;
    private final CityService cityService;

    @GetMapping
    public String getHotelCountInEachCity(Model model) {
        List<CityHotelSummaryDTO> hotelSummary = hotelService.getHotelCountInUzbekistanCities();
        model.addAttribute("summaries", hotelSummary);

        List<CityResponseDTO> cities = cityService.getAllCities();

        model.addAttribute("cities", cities);
        return "index";
    }

    @GetMapping("/search")
    public String getSearchPageResults(@RequestParam UUID cityId,
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam String guests, Model model) {

        List<CityResponseDTO> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        List<HotelSearchResponseDTO> searchResult = hotelService.search(cityId, checkIn, checkOut, guests);

        model.addAttribute("hotels", searchResult);

        searchResult.forEach(hotel -> log.error("Found hotel: {}  ->  {}  ->  {}", hotel.getName(), hotel.getPrice(),
                hotel.getMainImageUrl()));
        return "search";
    }

    @GetMapping("/hotels/{id}/see-rooms")
    public String getHotelAvailibilties(@PathVariable Long id, Model model) {
        HotelResponseDTO dto = hotelService.getHotel(id);
        model.addAttribute("hotel", dto);

        return "see-rooms";
    }

}
