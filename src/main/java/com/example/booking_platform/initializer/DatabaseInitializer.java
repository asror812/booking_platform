package com.example.booking_platform.initializer;

import com.example.booking_platform.exception.EntityNotFoundException;
import com.example.booking_platform.model.*;
import com.example.booking_platform.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

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

        private static final String ADMIN_ROLE = "ADMIN";
        private static final String USER_ROLE = "USER";
        private static final String MANAGER_ROLE = "MANAGER";

        private static final List<String> IMAGE_PATHS = List.of(
                        "/img/1.webp", "/img/2.webp", "/img/3.webp",
                        "/img/4.webp", "/img/5.webp", "/img/6.webp",
                        "/img/7.webp", "/img/8.webp", "/img/9.webp");

        @Override
        public void run(String... args) {
                createRoomFacilities();
                createCities();
                createHotels();
                createRoomTypes();
                createHotelFacilities();
                createHotelImages();
                createRoles();
                createUsers();
        }

        private void createCities() {
                if (cityRepository.count() > 0)
                        return;

                List<CityData> cityDataList = List.of(
                                new CityData("Andijon", "andijon", "/img/cities/andijon.png"),
                                new CityData("Buxoro", "buxoro", "/img/cities/buxoro.png"),
                                new CityData("Fargona", "fargona", "/img/cities/fargona.png"),
                                new CityData("Jizzax", "jizzax", "/img/cities/jizzax.png"),
                                new CityData("Namangan", "namangan", "/img/cities/namangan.png"),
                                new CityData("Samarkand", "samarkand", "/img/cities/samarkand.png"),
                                new CityData("Tashkent", "tashkent", "/img/cities/tashkent.png"));

                List<City> cities = cityDataList.stream()
                                .map(data -> new City(data.name, data.code, data.image, Collections.emptyList()))
                                .toList();

                cityRepository.saveAll(cities);
        }

        private void createRoomFacilities() {
                if (roomFacilityRepository.count() > 0)
                        return;

                List<String> facilities = List.of("WI-FI", "TV", "Towels", "Air Conditioner",
                                "Telephone", "Mini-Fridge", "Safe", "Hair Dryer", "Bathrobes",
                                "Coffee Maker", "Iron & Ironing Board", "Writing Desk");

                List<RoomFacility> roomFacilities = facilities.stream()
                                .map(RoomFacility::new)
                                .toList();

                roomFacilityRepository.saveAll(roomFacilities);
        }

        private void createRoles() {
                if (roleRepository.count() > 0)
                        return;

                Role userRole = new Role(USER_ROLE);
                Role adminRole = new Role(ADMIN_ROLE);
                Role managerRole = new Role(MANAGER_ROLE);

                roleRepository.saveAll(List.of(userRole, adminRole, managerRole));
        }

        private void createUsers() {
                if (userRepository.count() > 0)
                        return;

                Role userRole = roleRepository.findByName(USER_ROLE)
                                .orElseThrow(() -> new EntityNotFoundException("Role", USER_ROLE));

                Role adminRole = roleRepository.findByName(ADMIN_ROLE)
                                .orElseThrow(() -> new EntityNotFoundException("Role", ADMIN_ROLE));

                List<User> users = List.of(
                                new User("admin", passwordEncoder.encode("admin123"),
                                                "admin@booking.uz", List.of(adminRole)),
                                new User("mike_wilson", passwordEncoder.encode("user123"),
                                                "mike@example.com", List.of(userRole)),
                                new User("sarah_johnson", passwordEncoder.encode("user123"),
                                                "sarah@example.com", List.of(userRole)),
                                new User("david_brown", passwordEncoder.encode("user123"),
                                                "david@example.com", List.of(userRole)));

                userRepository.saveAll(users);
        }

        @Transactional
        private void createHotels() {
                if (hotelRepository.count() > 0)
                        return;

                Map<String, City> cities = getCityMap();
                List<Hotel> hotels = new ArrayList<>();

                // ANDIJON - 5 hotels
                hotels.addAll(createCityHotels(cities.get("andijon"), List.of(
                                new HotelData("National Andijon", "12 Amir Temur Street",
                                                "National Hotel offers modern design and rooftop restaurant in Andijon."),
                                new HotelData("Andijon Palace", "45 Bobur Avenue",
                                                "Andijon Palace provides luxury rooms, conference halls, and a spa center."),
                                new HotelData("Silk Valley Resort", "88 Mustaqillik Street",
                                                "Family-friendly resort with outdoor activities and traditional Uzbek hospitality."),
                                new HotelData("Andijon Business Hotel", "15 Navoi Avenue",
                                                "Perfect for business travelers with meeting rooms and high-speed internet."),
                                new HotelData("Green Garden Inn", "34 Shahristan Road",
                                                "Boutique hotel with beautiful gardens and organic restaurant."))));

                // BUXORO - 6 hotels
                hotels.addAll(createCityHotels(cities.get("buxoro"), List.of(
                                new HotelData("Miracle Buxoro", "8 Ismail Samani Street",
                                                "Classic elegance and historic charm with spa and fine dining."),
                                new HotelData("Silk Road Inn", "21 Khodja Nurobod Street",
                                                "Boutique hotel with traditional Uzbek architecture and garden."),
                                new HotelData("Old City Palace", "5 Poi Kalon Square",
                                                "Historic palace hotel near ancient monuments with rooftop terrace views."),
                                new HotelData("Bukhara Grand Hotel", "67 Alisher Navoi Street",
                                                "Modern luxury hotel with oriental decorations and international cuisine."),
                                new HotelData("Emir's Rest", "11 Lyabi Hauz Street",
                                                "Traditional guesthouse near the famous Lyabi Hauz complex."),
                                new HotelData("Caravan Saray Hotel", "29 Toki Sarrafon",
                                                "Authentic caravanserai experience with modern amenities."))));

                // FARGONA - 5 hotels
                hotels.addAll(createCityHotels(cities.get("fargona"), List.of(
                                new HotelData("Grand Plaza Fargona", "99 Mustaqillik Street",
                                                "Premier business hotel with conference rooms and spa."),
                                new HotelData("Fargona Oasis", "15 Navoi Avenue",
                                                "A relaxing oasis hotel with pool, fitness, and open-air restaurant."),
                                new HotelData("Asia Fergana", "42 Al-Fergani Street",
                                                "Comfortable hotel with Asian fusion restaurant and wellness center."),
                                new HotelData("Valley View Hotel", "78 Margilan Road",
                                                "Scenic location with mountain views and traditional crafts gallery."),
                                new HotelData("Fergana Park Hotel", "23 Amir Temur Park",
                                                "Park-side hotel perfect for families with playground and picnic areas."))));

                // JIZZAX - 5 hotels
                hotels.addAll(createCityHotels(cities.get("jizzax"), List.of(
                                new HotelData("Golden Tulip Jizzax", "30 Islam Karimov Street",
                                                "Elegant modern hotel with outdoor pool and fine dining."),
                                new HotelData("Jizzax Comfort", "18 Mustaqillik Avenue",
                                                "Affordable comfort hotel with modern amenities and breakfast included."),
                                new HotelData("Sarmish Resort", "52 Sarmish Road",
                                                "Mountain resort near ancient petroglyphs with hiking trails."),
                                new HotelData("Jizzakh Plaza", "9 Sharof Rashidov Avenue",
                                                "City center hotel with easy access to local markets and attractions."),
                                new HotelData("Zaamin Mountain Lodge", "71 Zaamin Road",
                                                "Mountain retreat with nature trails and stunning valley views."))));

                // TASHKENT - 8 hotels
                hotels.addAll(createCityHotels(cities.get("tashkent"), List.of(
                                new HotelData("National Tashkent", "1 Amir Temur Avenue",
                                                "Blend of culture and comfort with tranquil gardens and personalized services."),
                                new HotelData("City Palace Tashkent", "56 Shakhrisabz Street",
                                                "Luxury hotel with indoor pool and proximity to art galleries."),
                                new HotelData("Uzbekistan Tower Hotel", "12 Navoi Avenue",
                                                "Modern skyscraper hotel with panoramic views and rooftop bar."),
                                new HotelData("Hyatt Regency Tashkent", "1A Navoi Street",
                                                "Five-star international hotel with world-class dining and spa."),
                                new HotelData("Lotte City Hotel", "55 Navoi Street",
                                                "Premium hotel with shopping mall access and executive lounges."),
                                new HotelData("Wyndham Tashkent", "91 Amir Temur Street",
                                                "Contemporary hotel near business district with modern facilities."),
                                new HotelData("International Hotel Tashkent", "107A Amir Temur Avenue",
                                                "Historic hotel renovated with modern amenities and classic charm."),
                                new HotelData("Crowne Plaza Tashkent", "11 Bunyodkor Avenue",
                                                "Business hotel with conference center and international restaurants."))));

                // SAMARKAND - 6 hotels
                hotels.addAll(createCityHotels(cities.get("samarkand"), List.of(
                                new HotelData("Registan View Hotel", "3 Registan Street",
                                                "Located near Registan Square, featuring oriental-style rooms and spa."),
                                new HotelData("Samarkand Heritage Inn", "22 Tashkent Road",
                                                "A boutique inn with traditional interiors and local cuisine."),
                                new HotelData("Grand Samarkand Hotel", "88 Mirzo Ulugbek Street",
                                                "Luxurious hotel near Gur-Emir Mausoleum with gardens and pools."),
                                new HotelData("Silk Road Samarkand", "45 Shohruh Mirzo Street",
                                                "Elegant hotel celebrating Silk Road heritage with guided tours."),
                                new HotelData("Majestic Plaza", "12 Rudaki Avenue",
                                                "Modern comfort with traditional hospitality and rooftop dining."),
                                new HotelData("Orient Star Hotel", "33 Iskandar Street",
                                                "Boutique hotel with handcrafted interiors and cultural performances."))));

                // NAMANGAN - 5 hotels
                hotels.addAll(createCityHotels(cities.get("namangan"), List.of(
                                new HotelData("Namangan Plaza", "7 Babur Street",
                                                "Modern business hotel with fitness center and meeting halls."),
                                new HotelData("Orchard Inn Namangan", "25 Chust Road",
                                                "Cozy family-friendly hotel surrounded by fruit gardens."),
                                new HotelData("Namangan Grand", "14 Mashrab Street",
                                                "City center hotel with restaurant serving regional specialties."),
                                new HotelData("Fergana Valley Hotel", "39 Kosonsoy Road",
                                                "Comfortable lodging with easy access to valley attractions."),
                                new HotelData("Namangan Comfort Inn", "61 Islam Karimov Avenue",
                                                "Budget-friendly hotel with clean rooms and friendly service."))));

                hotelRepository.saveAll(hotels);
        }

        private List<Hotel> createCityHotels(City city, List<HotelData> hotelDataList) {
                return hotelDataList.stream()
                                .map(data -> Hotel.builder()
                                                .name(data.name)
                                                .city(city)
                                                .address(data.address + ", " + city.getName() + ", Uzbekistan")
                                                .description(data.description)
                                                .build())
                                .toList();
        }

        @Transactional
        public void createRoomTypes() {
                if (roomRepository.count() > 0)
                        return;

                List<Hotel> hotels = hotelRepository.findAll();
                List<RoomType> allRoomTypes = new ArrayList<>();
                List<Bed> allBeds = new ArrayList<>();
                List<Room> allRooms = new ArrayList<>();

                for (Hotel hotel : hotels) {
                        // Create 5 room types per hotel with varied pricing
                        List<RoomTypeConfig> configs = List.of(
                                        new RoomTypeConfig("Standard Single", 1, 50_000L, 3,
                                                        List.of(new BedConfig("Single", 1))),
                                        new RoomTypeConfig("Comfort Double", 2, 85_000L, 5,
                                                        List.of(new BedConfig("Double", 1))),
                                        new RoomTypeConfig("Deluxe Double", 2, 120_000L, 4,
                                                        List.of(new BedConfig("Queen", 1))),
                                        new RoomTypeConfig("Family Suite", 4, 180_000L, 3,
                                                        List.of(new BedConfig("King", 1), new BedConfig("Single", 2))),
                                        new RoomTypeConfig("Executive Suite", 3, 250_000L, 2,
                                                        List.of(new BedConfig("King", 2),
                                                                        new BedConfig("Sofa Bed", 1))));

                        for (RoomTypeConfig config : configs) {
                                RoomType roomType = new RoomType(hotel.getName() + " " + config.name,
                                                config.capacity, config.pricePerNight, Collections.emptySet(),
                                                Collections.emptyList(), Collections.emptyList(), hotel);

                                allRoomTypes.add(roomType);

                                List<Bed> beds = config.beds.stream()
                                                .map(bedConfig -> new Bed(bedConfig.type, bedConfig.count, roomType))
                                                .toList();
                                allBeds.addAll(beds);
                                roomType.setBeds(beds);

                                // Create multiple rooms for this room type
                                for (int i = 1; i <= config.roomCount; i++) {
                                        allRooms.add(new Room(i, roomType, hotel));
                                }
                        }
                }

                roomTypeRepository.saveAll(allRoomTypes);
                bedRepository.saveAll(allBeds);
                roomRepository.saveAll(allRooms);
        }

        @Transactional
        protected void createHotelFacilities() {
                if (hotelFacilityRepository.count() > 0)
                        return;

                Map<String, HotelFacility> facilities = createFacilitiesMap();
                hotelFacilityRepository.saveAll(facilities.values());

                List<Hotel> hotels = hotelRepository.findAllWithImagesAndFacilities();
                if (hotels.isEmpty())
                        return;

                List<List<String>> facilityPatterns = List.of(
                                List.of("wifi", "pool", "gym", "restaurant", "breakfast"),
                                List.of("wifi", "spa", "conference", "breakfast", "parking", "bar"),
                                List.of("wifi", "parking", "restaurant", "breakfast"),
                                List.of("wifi", "pool", "breakfast", "kidsClub", "restaurant"),
                                List.of("wifi", "spa", "restaurant", "bar", "shuttle", "gym"),
                                List.of("wifi", "parking", "breakfast", "restaurant"),
                                List.of("wifi", "pool", "gym", "conference", "bar", "restaurant"),
                                List.of("wifi", "restaurant", "kidsClub", "breakfast"),
                                List.of("wifi", "spa", "pool", "breakfast", "gym"),
                                List.of("wifi", "bar", "restaurant", "shuttle", "parking"));

                for (int i = 0; i < hotels.size(); i++) {
                        Hotel hotel = hotels.get(i);
                        List<String> pattern = facilityPatterns.get(i % facilityPatterns.size());

                        List<HotelFacility> hotelFacilities = pattern.stream()
                                        .map(facilities::get)
                                        .filter(Objects::nonNull)
                                        .toList();

                        hotel.getFacilities().addAll(hotelFacilities);
                }

                hotelRepository.saveAll(hotels);
        }

        private Map<String, HotelFacility> createFacilitiesMap() {
                Map<String, HotelFacility> facilities = new HashMap<>();

                List<FacilityData> facilityDataList = List.of(
                                new FacilityData("wifi", "Free WI-FI", "/img/icons/wi-fi.png"),
                                new FacilityData("parking", "Free Parking", "/img/icons/parking.png"),
                                new FacilityData("pool", "Swimming Pool", "/img/icons/pool.png"),
                                new FacilityData("breakfast", "Breakfast Included", "/img/icons/breakfast.png"),
                                new FacilityData("spa", "Spa & Wellness Center", "/img/icons/spa.png"),
                                new FacilityData("gym", "Fitness Center", "/img/icons/gym.png"),
                                new FacilityData("bar", "Bar & Lounge", "/img/icons/bar.png"),
                                new FacilityData("restaurant", "Restaurant", "/img/icons/restaurant.png"),
                                new FacilityData("kidsClub", "Kids Club", "/img/icons/kids.png"),
                                new FacilityData("shuttle", "Airport Shuttle", "/img/icons/shuttle.png"),
                                new FacilityData("conference", "Conference Hall", "/img/icons/conference.png"),
                                new FacilityData("laundry", "Laundry Service", "/img/icons/laundry.png"),
                                new FacilityData("concierge", "24/7 Concierge", "/img/icons/concierge.png"));

                facilityDataList.forEach(
                                data -> facilities.put(data.key, new HotelFacility(data.name, data.icon)));

                return facilities;
        }

        @Transactional
        private void createHotelImages() {
                if (hotelImageRepository.count() > 0)
                        return;

                List<Hotel> hotels = hotelRepository.findAllWithImagesAndFacilities();
                List<HotelImage> allImages = new ArrayList<>();
                int imgIndex = 0;

                for (Hotel hotel : hotels) {
                        List<HotelImage> hotelImages = new ArrayList<>();

                        for (int i = 0; i < 12; i++) {
                                String path = IMAGE_PATHS.get(imgIndex % IMAGE_PATHS.size());
                                boolean isMain = (i == 0);

                                HotelImage hotelImage = new HotelImage(path, isMain, hotel);
                                hotelImages.add(hotelImage);
                                imgIndex++;
                        }

                        hotel.getImages().addAll(hotelImages);
                        allImages.addAll(hotelImages);
                }

                hotelImageRepository.saveAll(allImages);
                hotelRepository.saveAll(hotels);
        }

        private Map<String, City> getCityMap() {
                return cityRepository.findAll().stream()
                                .collect(HashMap::new, (map, city) -> map.put(city.getCode(), city), HashMap::putAll);
        }

        private record CityData(String name, String code, String image) {
        }

        private record HotelData(String name, String address, String description) {
        }

        private record RoomTypeConfig(String name, int capacity, Long pricePerNight,
                        int roomCount, List<BedConfig> beds) {
        }

        private record BedConfig(String type, int count) {
        }

        private record FacilityData(String key, String name, String icon) {
        }
}