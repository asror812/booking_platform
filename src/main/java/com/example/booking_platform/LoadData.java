package com.example.booking_platform;

import com.example.booking_platform.enums.City;
import com.example.booking_platform.model.*;
import com.example.booking_platform.repository.*;
import com.example.booking_platform.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final HotelAmenityRepository hotelAmenityRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoomTypeRepository roomTypeRepository;

    private final BedRepository bedRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createHotels();
        createRooms();
        createHotelAmenities();
        /*createRoomTypes();*/
        createRoles();
        createUsers();
    }

    private void createRoles() {
        if(roleRepository.count() == 0){

        }
    }

    private void createUsers() {
        if(userRepository.count() == 0){

            Role userRole = new Role(null , "USER" , new ArrayList<>());
            Role adminRole = new Role(null , "ADMIN" , new ArrayList<>());

            roleRepository.saveAll(List.of(userRole , adminRole));

         /*   Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new SpringMVCException.RoleNotFoundException("USER"));

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new SpringMVCException.RoleNotFoundException("ADMIN"));
*/

            User admin = new User(null ,  "asror" , passwordEncoder.encode("123") ,
                    "asror@gmail.com"  , new ArrayList<>());

            User user = new User(null , "string" , passwordEncoder.encode("123") ,
                    "string@gmail.com" , new ArrayList<>());
            userRepository.saveAll(List.of(admin, user));

            userRole.getUsers().add(user);
            adminRole.getUsers().add(admin);
            admin.getRoles().add(adminRole);
            user.getRoles().add(userRole);

            roleRepository.saveAll(List.of(adminRole));
            userRepository.saveAll(List.of(admin , user));
        }
    }


    protected void createHotelAmenities() {
        if (hotelAmenityRepository.count() == 0) {
         List<Hotel> hotels = hotelRepository.getThreeHotels((PageRequest.of(0 , 3)));


            HotelAmenity wifi = new HotelAmenity(null ,"Free wi fi", true, 0L, new ArrayList<>());
            HotelAmenity parking = new HotelAmenity(null, "Free Parking", true, 0L, new ArrayList<>());
            HotelAmenity pool = new HotelAmenity(null ,"Pool", false, 15L, new ArrayList<>());
            HotelAmenity breakfast = new HotelAmenity(null ,"Breakfast", false, 20L, new ArrayList<>());

            hotelAmenityRepository.saveAll(List.of(wifi , parking , pool , breakfast));

            for (Hotel hotel : hotels) {
                Hibernate.initialize(hotel.getAmenities());
              wifi.getHotels().add(hotel);
              parking.getHotels().add(hotel);
              pool.getHotels().add(hotel);
              breakfast.getHotels().add(hotel);
              hotel.getAmenities().addAll(List.of(wifi, parking , pool , breakfast));
            }

            hotelAmenityRepository.saveAll(List.of(wifi , parking , pool , breakfast));
            hotelRepository.saveAll(hotels);
        }
    }


    public void createRooms() {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(0, 3);
        List<Hotel> hotels = hotelRepository.getThreeHotels(pageable);

        if (roomRepository.count() == 0) {
            List<RoomType> roomTypes = new ArrayList<>();
            List<Room> rooms = new ArrayList<>();
            List<Bed> beds = new ArrayList<>();


            int i = 1;
            for (Hotel hotel : hotels) {
                RoomType roomType1 = new RoomType(null, "Classic" ,"Classic Room, featuring 1 Queen Bed and 1 Single Bed, ideal for small families", 2, 50_000L, Collections.emptyList(), Collections.emptyList(), hotel);
                RoomType roomType2 = new RoomType(null, "Deluxe" ,"Deluxe Room, with 1 King Bed and 2 Single Beds, perfect for families seeking extra space and comfort." ,  4, 100_000L, Collections.emptyList(), Collections.emptyList(), hotel);
                RoomType roomType3 = new RoomType(null, "Executive" , "Executive Suite, with 2 King Beds and a Sofa Bed, offering luxury and space for larger groups.", 6, 200_000L, Collections.emptyList(), Collections.emptyList(), hotel);
                roomTypes.addAll(List.of(roomType1, roomType2, roomType3));

                Room room1 = new Room(null, i, roomType1, hotel);
                Room room2 = new Room(null, i + 1, roomType2, hotel);
                Room room3 = new Room(null, i + 2, roomType3, hotel);

                rooms.addAll(List.of(room1, room2, room3));
                beds.addAll(createBedsForRoomTypes(roomType1, roomType2, roomType3));
                i = i + 3;
            }

            // Save all entities in batch
            roomTypeRepository.saveAll(roomTypes);
            roomRepository.saveAll(rooms);
            bedRepository.saveAll(beds);
        }
    }

    private List<Bed> createBedsForRoomTypes(RoomType roomType1, RoomType roomType2, RoomType roomType3) {
        // Beds for Room Type 1 (Standard Room)
        Bed bed1 = new Bed(null, "Queen", 1, roomType1);
        Bed bed2 = new Bed(null, "Single", 1, roomType1);
        roomType1.setBeds(List.of(bed1, bed2));

        // Beds for Room Type 2 (Deluxe Room)
        Bed bed3 = new Bed(null, "King", 1, roomType2);
        Bed bed4 = new Bed(null, "Single", 2, roomType2);
        roomType2.setBeds(List.of(bed3, bed4));

        // Beds for Room Type 3 (Suite)
        Bed bed5 = new Bed(null, "King", 2, roomType3);
        Bed bed6 = new Bed(null, "Sofa Bed", 1, roomType3);
        roomType3.setBeds(List.of(bed5, bed6));

        return List.of(bed1, bed2, bed3, bed4, bed5, bed6);
    }

    private void createHotels() {
        if (hotelRepository.count() == 0) {
            Hotel hotel1 = new Hotel(null, "National", City.ANDIJON, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                    "National Hotel offers a modern and minimalist design, located in the heart of Andijon. Enjoy a peaceful stay with top-notch amenities including free high-speed Wi-Fi, a state-of-the-art gym, and an exquisite rooftop restaurant.",
                    true, "1.webp");

            Hotel hotel2 = new Hotel(null, "Miracle", City.BUXORO, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Miracle Hotel in Buxoro is renowned for its classic elegance and historic charm. Located near Buxoro’s top landmarks, this luxury hotel provides spacious rooms, a world-class spa, and fine dining experiences.",
                    true, "2.webp");

            Hotel hotel3 = new Hotel(null, "Luxury Inn", City.TASHKENT, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                    "Luxury Inn in Tashkent combines contemporary comfort with traditional Uzbek hospitality. Experience deluxe rooms with beautiful city views, gourmet cuisine, and an exclusive rooftop bar for unforgettable evenings.",
                    true, "3.webp");

            Hotel hotel4 = new Hotel(null, "Heritage Hotel", City.SAMARQAND, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Heritage Hotel is nestled near the historical sites of Samarqand. A perfect blend of culture and comfort, guests can enjoy personalized services, tranquil gardens, and easy access to the city’s ancient wonders.",
                    true, "4.webp");
/*
            Hotel hotel5 = new Hotel(null, "City Palace", City.NUKUS, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                    "City Palace in Nukus offers a royal stay with luxurious interiors and top-of-the-line amenities. The hotel features an indoor pool, fine dining, and proximity to local museums and art galleries.",
                    true, "5.webp");

            Hotel hotel6 = new Hotel(null, "Grand Plaza", City.FARGONA, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Grand Plaza in Fargona is a premier hotel for business and leisure travelers. With spacious conference rooms, a relaxing spa, and gourmet dining options, this hotel guarantees a refined stay for all guests.",
                    true, "6.webp");

            Hotel hotel7 = new Hotel(null, "Golden Tulip", City.JIZZAX, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Golden Tulip is Jizzax's finest hotel, offering an elegant setting with modern accommodations. Guests can enjoy relaxing in the outdoor pool, savor fine international cuisine, and unwind at the hotel’s exclusive lounge.",
                    true, "7.webp");

            Hotel hotel8 = new Hotel(null, "Mountain View", City.NAVOIY, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Mountain View Hotel in Navoi offers breathtaking views of the surrounding mountains. This cozy hotel features comfortable rooms, a serene atmosphere, and outdoor activities for adventure seekers.",
                    true, "8.webp");

            Hotel hotel9 = new Hotel(null, "Central Park Hotel", City.QARSHI, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Central Park Hotel in Karshi provides guests with a peaceful retreat in a lush, park-side location. The hotel offers luxurious rooms, a fitness center, and dining options with fresh, locally sourced ingredients.",
                    true, "9.webp");

            Hotel hotel10 = new Hotel(null, "Regal Stay", City.TERMIZ, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),
                    "Regal Stay in Termiz combines comfort and affordability. The hotel features modern rooms, a welcoming staff, and a convenient location near the city’s main attractions and business district.",
                    true, "10.webp");
*/

            hotelRepository.saveAll(List.of(hotel1, hotel2, hotel3, hotel4));
        }
    }
}
