package com.example.booking_platform.hotel;



import com.example.booking_platform.address.City;
import com.example.booking_platform.amentity.HotelAmenityService;
import com.example.booking_platform.hotel.dto.*;
import com.example.booking_platform.room.RoomService;
import com.example.booking_platform.room.dto.RoomResponseDTO;
import com.example.booking_platform.room.dto.RoomTypeResponseDTO;
import jakarta.validation.Valid;
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
    private final HotelAmenityService amenityService;

    private final RoomService roomService;

    @PostMapping("/create")
    public String createHotel(@Valid @ModelAttribute HotelCreateDTO hotelCreateDTO) {
        hotelService.create(hotelCreateDTO);
        return "redirect:/admin";
    }

    @GetMapping("/hotel/create-page")
    public String getCreateHotelPage() {
        return "hotel/create";
    }



    @GetMapping
    public String getAllHotels(Model model ) {

        List<HotelResponseDTO> all = hotelService.getAll();
        model.addAttribute("hotels", all);

        return "index";
    }

    @GetMapping("{id}/see-rooms")
    public String getRoomsPage(@PathVariable Long id, Model model) {
        HotelResponseDTO responseDTO = hotelService.getHotel(id);
        model.addAttribute("hotel", responseDTO);

        return "hotel/rooms";
    }


    @GetMapping("/{id}")
    public String getHotelById(@PathVariable Long id , Model model) {
        HotelResponseDTO hotel = hotelService.getHotel(id);
        model.addAttribute("hotel", hotel);

        return "index";
    }


 /*   @GetMapping("/{id}/update")
    public String downloadPicture(@PathVariable String id , HttpServletResponse response) {
       return hotelService.downloadFile(id , response);
    }
*/



    @GetMapping("/{id}/update")
    public String getEditHotelPage(@PathVariable Long id , Model model) {

        HotelResponseDTO hotel = hotelService.getHotel(id);

        List<RoomTypeResponseDTO> roomTypes = hotel.getRoomTypes();


        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("hotel", hotel);


        return "hotel/update" ;
    }


    @PutMapping("/{id}")
    public String editHotel(@PathVariable Long id , @ModelAttribute @Valid HotelUpdateDTO updateDTO) {
         hotelService.update(id , updateDTO);
         return "redirect:/%s/update".formatted(id.toString());
    }

    @GetMapping("/hotel/{id}/add-amenity")
    public String getAddAmenityPage(@PathVariable Long id , Model model) {
        HotelResponseDTO hotel = hotelService.getHotel(id);
        List<HotelAmenityResponseDTO> amenities = hotelService.getOther(id);


        model.addAttribute("hotel", hotel);
        model.addAttribute("amenities", amenities);


        return "hotel/add-amenity";
    }

    @PostMapping("/hotel/add-amenity")
    public String addAmenity(@ModelAttribute @Valid AddHotelAmenityDTO dto){
        hotelService.addAmenity(dto);

        return "redirect:/hotel/%s/add-amenity".formatted(dto.getHotelId());
    }

    @GetMapping("/hotel/{id}/rooms")
    public String getHotelRoomsPage(@PathVariable Long id) {
        List<RoomResponseDTO> hotelRooms = roomService.getHotelRooms(id);
        return "hotel/update";
    }


    @DeleteMapping("/hotel/remove-amenity")
    public String removeAmenity(@RequestParam(name = "hotelId") Long hotelId ,
                                @RequestParam(name = "amenityId") Integer amenityId) {

           hotelService.removeAmenity(hotelId , amenityId);


           return "redirect:/hotel/%s/add-amenity".formatted(hotelId);
    }

    @DeleteMapping("/hotel/delete-room")
    public String deleteRoom(@RequestParam(name = "hotelId") Long hotelId,
                             @RequestParam(name = "roomId") UUID roomId) {

        hotelService.deleteRoom(hotelId , roomId);

        return "redirect:/%s/update".formatted(hotelId);
    }


    @PostMapping("/hotel/search")
    public String searchForAdmin(@ModelAttribute HotelSearchForUserDTO dto ,
                                 Model model){


        List<HotelResponseDTO> hotels = hotelService.searchHotels(dto);

        model.addAttribute("hotels" , hotels);

        return "hotel/search";
    }






}
