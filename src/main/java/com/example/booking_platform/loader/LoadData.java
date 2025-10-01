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

    @Transactional
    private void createHotelImages() {
        if (hotelImageRepository.count() == 0) {
            List<Hotel> hotels = hotelRepository.findAllWithImagesAndFacilities();

            List<String> imagePaths = List.of(
                    "/img/1.webp", "/img/2.webp", "/img/3.webp",
                    "/img/4.webp", "/img/5.webp", "/img/6.webp",
                    "/img/7.webp", "/img/8.webp", "/img/9.webp");

            List<HotelImage> allImages = new ArrayList<>();

            int imgIndex = 0;

            for (Hotel hotel : hotels) {
                List<HotelImage> hotelImages = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    String path = imagePaths.get(imgIndex % imagePaths.size());
                    boolean isMain = (i == 0);

                    HotelImage hotelImage = new HotelImage(null, path, isMain, hotel);
                    hotelImages.add(hotelImage);
                    imgIndex++;
                }

                hotel.getImages().addAll(hotelImages);
                allImages.addAll(hotelImages);
            }

            hotelImageRepository.saveAll(allImages);
            hotelRepository.saveAll(hotels);
        }
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

    @Transactional
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
            City samarkand = cityRepository.findByCode("samarkand")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Samarkand"));
            City namangan = cityRepository.findByCode("namangan")
                    .orElseThrow(() -> new EntityNotFoundException("City", "Namangan"));

            List<Hotel> hotels = new ArrayList<>();

            // ANDIJON
            hotels.add(Hotel.builder()
                    .name("National Andijon")
                    .city(andijon)
                    .address("12 Amir Temur Street, Andijon, Uzbekistan")
                    .description("National Hotel offers modern design and rooftop restaurant in Andijon.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Andijon Palace")
                    .city(andijon)
                    .address("45 Bobur Avenue, Andijon, Uzbekistan")
                    .description("Andijon Palace provides luxury rooms, conference halls, and a spa center.")
                    .build());

            // BUXORO
            hotels.add(Hotel.builder()
                    .name("Miracle Buxoro")
                    .city(buxoro)
                    .address("8 Ismail Samani Street, Bukhara, Uzbekistan")
                    .description("Classic elegance and historic charm with spa and fine dining.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Silk Road Inn")
                    .city(buxoro)
                    .address("21 Khodja Nurobod Street, Bukhara, Uzbekistan")
                    .description("Silk Road Inn is a boutique hotel with traditional Uzbek architecture and garden.")
                    .build());

            // FARGONA
            hotels.add(Hotel.builder()
                    .name("Grand Plaza Fargona")
                    .city(fargona)
                    .address("99 Mustaqillik Street, Fergana, Uzbekistan")
                    .description("Premier business hotel with conference rooms and spa.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Fargona Oasis")
                    .city(fargona)
                    .address("15 Navoi Avenue, Fergana, Uzbekistan")
                    .description("A relaxing oasis hotel with pool, fitness, and open-air restaurant.")
                    .build());

            // JIZZAX
            hotels.add(Hotel.builder()
                    .name("Golden Tulip Jizzax")
                    .city(jizzax)
                    .address("30 Islam Karimov Street, Jizzakh, Uzbekistan")
                    .description("Elegant modern hotel with outdoor pool and fine dining.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Jizzax Comfort")
                    .city(jizzax)
                    .address("18 Mustaqillik Avenue, Jizzakh, Uzbekistan")
                    .description("Affordable comfort hotel with modern amenities and breakfast included.")
                    .build());

            // TASHKENT
            hotels.add(Hotel.builder()
                    .name("National Tashkent")
                    .city(tashkent)
                    .address("1 Amir Temur Avenue, Tashkent, Uzbekistan")
                    .description("Blend of culture and comfort with tranquil gardens and personalized services.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("City Palace Tashkent")
                    .city(tashkent)
                    .address("56 Shakhrisabz Street, Tashkent, Uzbekistan")
                    .description("Luxury hotel with indoor pool and proximity to art galleries.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Uzbekistan Tower Hotel")
                    .city(tashkent)
                    .address("12 Navoi Avenue, Tashkent, Uzbekistan")
                    .description("Modern skyscraper hotel with panoramic views and rooftop bar.")
                    .build());

            // SAMARKAND
            hotels.add(Hotel.builder()
                    .name("Registan View Hotel")
                    .city(samarkand)
                    .address("3 Registan Street, Samarkand, Uzbekistan")
                    .description("Located near Registan Square, featuring oriental-style rooms and spa.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Samarkand Heritage Inn")
                    .city(samarkand)
                    .address("22 Tashkent Road, Samarkand, Uzbekistan")
                    .description("A boutique inn with traditional interiors and local cuisine.")
                    .build());

            // NAMANGAN
            hotels.add(Hotel.builder()
                    .name("Namangan Plaza")
                    .city(namangan)
                    .address("7 Babur Street, Namangan, Uzbekistan")
                    .description("Modern business hotel with fitness center and meeting halls.")
                    .build());

            hotels.add(Hotel.builder()
                    .name("Orchard Inn Namangan")
                    .city(namangan)
                    .address("25 Chust Road, Namangan, Uzbekistan")
                    .description("Cozy family-friendly hotel surrounded by fruit gardens.")
                    .build());

            hotelRepository.saveAll(hotels);
        }
    }

    @Transactional
    protected void createHotelFacilities() {
        if (hotelFacilityRepository.count() == 0) {

            HotelFacility wifi = new HotelFacility(null, "Free WI-FI", "/img/wi-fi.png");
            HotelFacility parking = new HotelFacility(null, "Free Parking", "/img/wi-fi.png");
            HotelFacility pool = new HotelFacility(null, "Swimming Pool", "/img/wi-fi.png");
            HotelFacility breakfast = new HotelFacility(null, "Breakfast", "/img/wi-fi.png");
            HotelFacility spa = new HotelFacility(null, "Spa & Wellness Center", "/img/wi-fi.png");
            HotelFacility gym = new HotelFacility(null, "Fitness Center", "/img/wi-fi.png");
            HotelFacility bar = new HotelFacility(null, "Bar & Lounge", "/img/wi-fi.png");
            HotelFacility restaurant = new HotelFacility(null, "Restaurant", "/img/wi-fi.png");
            HotelFacility kidsClub = new HotelFacility(null, "Kids Club", "/img/wi-fi.png");
            HotelFacility shuttle = new HotelFacility(null, "Airport Shuttle", "/img/wi-fi.png");
            HotelFacility conference = new HotelFacility(null, "Conference Hall", "/img/wi-fi.png");

            hotelFacilityRepository.saveAll(List.of(
                    wifi, parking, pool, breakfast, spa, gym, bar, restaurant, kidsClub, shuttle, conference));

            List<Hotel> hotels = hotelRepository.findAllWithImagesAndFacilities();

            if (!hotels.isEmpty()) {
                hotels.get(0).getFacilities().addAll(List.of(wifi, pool, gym, restaurant));
                hotels.get(1).getFacilities().addAll(List.of(wifi, spa, conference, breakfast));
                hotels.get(2).getFacilities().addAll(List.of(wifi, parking, bar, restaurant));
                hotels.get(3).getFacilities().addAll(List.of(wifi, pool, breakfast, kidsClub));
                hotels.get(4).getFacilities().addAll(List.of(wifi, spa, restaurant, bar, shuttle));
                hotels.get(5).getFacilities().addAll(List.of(wifi, parking, breakfast));
                hotels.get(6).getFacilities().addAll(List.of(wifi, pool, gym, conference, bar));
                hotels.get(7).getFacilities().addAll(List.of(wifi, restaurant, kidsClub));
                hotels.get(8).getFacilities().addAll(List.of(wifi, spa, pool, breakfast));
                hotels.get(9).getFacilities().addAll(List.of(wifi, bar, restaurant, shuttle));
                hotels.get(10).getFacilities().addAll(List.of(wifi, gym, conference, restaurant));
                hotels.get(11).getFacilities().addAll(List.of(wifi, spa, pool));
                hotels.get(12).getFacilities().addAll(List.of(wifi, breakfast, bar, kidsClub));
                hotels.get(13).getFacilities().addAll(List.of(wifi, restaurant, parking));
            }

            hotelRepository.saveAll(hotels);
        }
    }

}
