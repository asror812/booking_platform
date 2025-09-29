package com.example.booking_platform.loader;

import com.example.booking_platform.exception.EntityNotFoundException;
import com.example.booking_platform.model.*;
import com.example.booking_platform.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final HotelFacilityRepository hotelFacilityRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoomTypeRepository roomTypeRepository;

    private final BedRepository bedRepository;
    private final RoleRepository roleRepository;
    private final RoomFacilityRepository roomFacilityRepository;

    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    private final HotelImageRepository hotelImageRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoomFacilities();

        createCities();

        createHotels();

        createRoomTypes();

        createHotelFacilities();
        /*  */

        createRoles();
        createUsers();

        createHotelImages();
    }

    private void createCities() {
        if (cityRepository.count() == 0) {
            City andijon = new City(null, "Andijon", "andijon", "/img/andijon.png",
                    Collections.emptyList());
            City buxoro = new City(null, "Buxoro", "buxoro", "/img/buxoro.png", Collections.emptyList());
            City fargona = new City(null, "Fargona", "fargona", "/img/fargona.png",
                    Collections.emptyList());
            City jizzax = new City(null, "Jizzax", "jizzax", "/img/jizzax.png", Collections.emptyList());
            City namangan = new City(null, "Namangan", "namangan", "/img/namangan.png",
                    Collections.emptyList());
            City samarkand = new City(null, "Samarkan", "samarkand", "/img/samarkand.png",
                    Collections.emptyList());
            City tashkent = new City(null, "Tashkent", "tashkent", "/img/tashkent.png",
                    Collections.emptyList());

            cityRepository.saveAll(
                    List.of(andijon, buxoro, fargona, jizzax, namangan,
                            samarkand, tashkent));
        }
    }

    public void createRoomFacilities() {
        if (roomFacilityRepository.count() == 0) {
            RoomFacility wifi = new RoomFacility(null, "WI-FI");
            RoomFacility tv = new RoomFacility(null, "TV");
            RoomFacility towel = new RoomFacility(null, "Towels");

            RoomFacility airConditioning = new RoomFacility(null, "Air Conditioner");
            RoomFacility telephone = new RoomFacility(null, "Telephone");
            RoomFacility miniFridge = new RoomFacility(null, "Mini-Fridge");

            roomFacilityRepository
                    .saveAll(List.of(wifi, tv, towel, airConditioning, telephone, miniFridge));
        }

    }

    /*
     * public void createRoomTypes() {
     * if (roomTypeRepository.count() == 0) {
     * 
     * List<RoomFacility> roomFacilities = roomFacilityRepository.findAll();
     * 
     * RoomType roomType1 = new RoomType(null, "Delux Single Room", 3, 500_000l,
     * roomFacilities,
     * Collections.emptyList(),
     * Collections.emptyList(), null);
     * 
     * RoomType roomType2 = new RoomType(null, "Delux Double Room", 3, 500_000l,
     * roomFacilities,
     * Collections.emptyList(),
     * Collections.emptyList(), null);
     * 
     * roomTypeRepository.saveAll(List.of(roomType1, roomType2));
     * }
     * }
     */

    @Transactional
    private void createHotelImages() {

        if (hotelImageRepository.count() == 0) {
            HotelImage image1 = new HotelImage(null, "/img/1.webp", true, null);
            HotelImage image2 = new HotelImage(null, "/img/2.webp", false, null);

            HotelImage image3 = new HotelImage(null, "/img/3.webp", true, null);
            HotelImage image4 = new HotelImage(null, "/img/4.webp", false, null);

            HotelImage image5 = new HotelImage(null, "/img/5.webp", true, null);
            HotelImage image6 = new HotelImage(null, "/img/6.webp", false, null);

            HotelImage image7 = new HotelImage(null, "/img/7.webp", true, null);

            HotelImage image8 = new HotelImage(null, "/img/8.webp", true, null);
            HotelImage image9 = new HotelImage(null, "/img/9.webp", false, null);

            List<Hotel> hotels = hotelRepository.findAllWithImagesAndFacilities();

            Hotel hotel1 = hotels.get(0);
            image1.setHotel(hotel1);
            image2.setHotel(hotel1);
            hotel1.getImages().addAll(List.of(image1, image2));

            Hotel hotel2 = hotels.get(1);
            image3.setHotel(hotel2);
            image4.setHotel(hotel2);
            hotel2.getImages().addAll(List.of(image3, image4));

            Hotel hotel3 = hotels.get(2);
            image5.setHotel(hotel3);
            image6.setHotel(hotel3);
            hotel3.getImages().addAll(List.of(image5, image6));

            Hotel hotel4 = hotels.get(3);
            image7.setHotel(hotel4);
            hotel4.getImages().add(image7);

            Hotel hotel5 = hotels.get(4);
            image8.setHotel(hotel5);
            image9.setHotel(hotel5);
            hotel5.getImages().addAll(List.of(image8, image9));

            hotelImageRepository.saveAll(List.of(image1, image2, image3, image4, image5,
                    image6, image7, image8, image9));
            hotelRepository.saveAll(List.of(hotel1, hotel2, hotel3, hotel4, hotel5));
        }

    }

    private void createRoles() {
        if (roleRepository.count() == 0) {

            Role userRole = new Role(null, "USER");
            Role adminRole = new Role(null, "ADMIN");

            roleRepository.saveAll(List.of(userRole, adminRole));
        }
    }

    private void createUsers() {
        if (userRepository.count() == 0) {

            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new EntityNotFoundException("Role", "USER"));

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new EntityNotFoundException("Role", "ADMIN"));

            User admin = new User(null, "asror", passwordEncoder.encode("123"),
                    "asror@gmail.com", List.of(adminRole));

            User user = new User(null, "farhod", passwordEncoder.encode("123"),
                    "string@gmail.com", List.of(userRole));
            userRepository.saveAll(List.of(admin, user));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    @Transactional
    protected void createHotelFacilities() {
        if (hotelFacilityRepository.count() == 0) {
            List<Hotel> hotels = hotelRepository.findAllWithImagesAndFacilities();

            HotelFacility wifi = new HotelFacility(null, "Free wi fi");
            HotelFacility parking = new HotelFacility(null, "Free Parking");
            HotelFacility pool = new HotelFacility(null, "Pool");
            HotelFacility breakfast = new HotelFacility(null, "Breakfast");

            hotelFacilityRepository.saveAll(List.of(wifi, parking, pool, breakfast));

            for (Hotel hotel : hotels) {
                hotel.getFacilities().addAll(List.of(wifi, parking, pool, breakfast));
            }

            hotelRepository.saveAll(hotels);
        }
    }

    public void createRoomTypes() {
        List<Hotel> hotels = hotelRepository.findAll();

        if (roomRepository.count() == 0) {
            List<RoomType> roomTypes = new ArrayList<>();
            List<Bed> beds = new ArrayList<>();
            List<Room> rooms = new ArrayList<>();

            for (Hotel hotel : hotels) {
                RoomType roomType1 = new RoomType(null, hotel.getName() + " Classic Single",
                        1, 50_000L, Collections.emptySet(), Collections.emptyList(),
                        Collections.emptyList(), hotel);

                RoomType roomType2 = new RoomType(null, hotel.getName() + " Deluxe Double",
                        2, 100_000L, Collections.emptySet(), Collections.emptyList(),
                        Collections.emptyList(), hotel);

                RoomType roomType3 = new RoomType(null, hotel.getName() + " Executive Triple Room",
                        3, 200_000L, Collections.emptySet(), Collections.emptyList(),
                        Collections.emptyList(), hotel);

                roomTypes.addAll(List.of(roomType1, roomType2, roomType3));

                Room room1 = new Room(null, 1, roomType1, hotel);
                Room room2 = new Room(null, 2, roomType2, hotel);
                Room room3 = new Room(null, 3, roomType3, hotel);

                rooms.addAll(List.of(room1, room2, room3));

                beds.addAll(createBedsForRoomTypes(roomType1, roomType2, roomType3));
            }

            // Save all entities in batch
            roomTypeRepository.saveAll(roomTypes);
            bedRepository.saveAll(beds);

            roomRepository.saveAll(rooms);
        }
    }

    private List<Bed> createBedsForRoomTypes(RoomType roomType1, RoomType roomType2, RoomType roomType3) {
        // Beds for Room Type 1 (Classic Single Room)
        Bed bed1 = new Bed(null, "Single", 1, roomType1);
        roomType1.setBeds(List.of(bed1));

        // Beds for Room Type 2 (Deluxe Double Room)
        Bed bed2 = new Bed(null, "King", 1, roomType2);
        Bed bed3 = new Bed(null, "Single", 1, roomType2);
        roomType2.setBeds(List.of(bed2, bed3));

        // Beds for Room Type 3 (Suite Triple Room)
        Bed bed4 = new Bed(null, "King", 2, roomType3);
        Bed bed5 = new Bed(null, "Sofa Bed", 1, roomType3);
        roomType3.setBeds(List.of(bed4, bed5));

        return List.of(bed1, bed2, bed3, bed4, bed5);
    }

    private void createHotels() {

        if (hotelRepository.count() == 0) {
            City andijon = cityRepository.findByCode("andijon")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Andijon"));

            City buxoro = cityRepository.findByCode("buxoro")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Buxoro"));

            City fargona = cityRepository.findByCode("fargona")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Fargona"));

            City jizzax = cityRepository.findByCode("jizzax")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Jizzax"));

            City tashkent = cityRepository.findByCode("tashkent")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Tashkent"));

            Hotel hotel1 = Hotel.builder()
                    .name("National")
                    .city(andijon)
                    .description(
                            "National Hotel offers a modern and minimalist design, located in the heart of Andijon. Enjoy a peaceful stay with top-notch amenities including free high-speed Wi-Fi, a state-of-the-art gym, and an exquisite rooftop restaurant.")
                    .build();

            Hotel hotel2 = Hotel.builder()
                    .name("Miracle")
                    .city(buxoro)
                    .description(
                            "Miracle Hotel in Buxoro is renowned for its classic elegance and historic charm. Located near Buxoro's top landmarks, this luxury hotel provides spacious rooms, a world-class spa, and fine dining experiences.")
                    .build();

            Hotel hotel3 = Hotel.builder()
                    .name("National Hotel")
                    .city(tashkent)
                    .description(
                            "National Hotel is the best hotel build in Uzbekistan. A perfect blend of culture and comfort, guests can enjoy personalized services, tranquil gardens, and easy access to the city's ancient wonders.")
                    .build();

            Hotel hotel4 = Hotel.builder()
                    .name("City Palace")
                    .description(
                            "City Palace in Nukus offers a royal stay with luxurious interiors and top-of-the-line amenities. The hotel features an indoor pool, fine dining, and proximity to local museums and art galleries.")
                    .city(tashkent)
                    .build();

            Hotel hotel5 = Hotel.builder()
                    .name("Grand Plaza")
                    .city(fargona)
                    .description(
                            "Grand Plaza in Fargona is a premier hotel for business and leisure travelers. With spacious conference rooms, a relaxing spa, and gourmet dining options, this hotel guarantees a refined stay for all guests.")
                    .build();

            Hotel hotel6 = Hotel.builder()
                    .name("Golden Tulip")
                    .city(jizzax)
                    .description(
                            "Golden Tulip is Jizzax's finest hotel, offering an elegant setting with modern accommodations. Guests can enjoy relaxing in the outdoor pool, savor fine international cuisine, and unwind at the hotel’s exclusive lounge.")
                    .build();

            hotelRepository.saveAll(List.of(hotel1, hotel2, hotel3, hotel4, hotel5, hotel6));
        }
    }
}
