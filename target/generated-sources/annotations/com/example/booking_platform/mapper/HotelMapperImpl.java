package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.HotelFacilityResponseDTO;
import com.example.booking_platform.dto.response.HotelImageResponseDTO;
import com.example.booking_platform.dto.response.HotelResponseDTO;
import com.example.booking_platform.dto.response.HotelSearchResponseDTO;
import com.example.booking_platform.dto.response.RoomTypeResponseDTO;
import com.example.booking_platform.model.Hotel;
import com.example.booking_platform.model.HotelFacility;
import com.example.booking_platform.model.HotelImage;
import com.example.booking_platform.model.RoomType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T17:09:01+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class HotelMapperImpl implements HotelMapper {

    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private HotelFacilityMapper hotelFacilityMapper;
    @Autowired
    private CityMapper cityMapper;

    @Override
    public HotelSearchResponseDTO toSearchResponseDTO(Hotel hotel, Integer guestsCount) {
        if ( hotel == null ) {
            return null;
        }

        HotelSearchResponseDTO hotelSearchResponseDTO = new HotelSearchResponseDTO();

        hotelSearchResponseDTO.setDescription( hotel.getDescription() );
        hotelSearchResponseDTO.setId( hotel.getId() );
        hotelSearchResponseDTO.setName( hotel.getName() );

        hotelSearchResponseDTO.setFacilities( mapFacilities(hotel.getFacilities()) );

        enrich( hotelSearchResponseDTO, hotel, guestsCount );

        return hotelSearchResponseDTO;
    }

    @Override
    public HotelResponseDTO toResponseDTO(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        HotelResponseDTO hotelResponseDTO = new HotelResponseDTO();

        hotelResponseDTO.setAddress( hotel.getAddress() );
        hotelResponseDTO.setCity( cityMapper.toResponseDTO( hotel.getCity() ) );
        hotelResponseDTO.setDescription( hotel.getDescription() );
        hotelResponseDTO.setFacilities( hotelFacilitySetToHotelFacilityResponseDTOSet( hotel.getFacilities() ) );
        hotelResponseDTO.setId( hotel.getId() );
        hotelResponseDTO.setImages( hotelImageListToHotelImageResponseDTOList( hotel.getImages() ) );
        hotelResponseDTO.setName( hotel.getName() );
        hotelResponseDTO.setRoomTypes( roomTypeListToRoomTypeResponseDTOList( hotel.getRoomTypes() ) );

        return hotelResponseDTO;
    }

    protected Set<HotelFacilityResponseDTO> hotelFacilitySetToHotelFacilityResponseDTOSet(Set<HotelFacility> set) {
        if ( set == null ) {
            return null;
        }

        Set<HotelFacilityResponseDTO> set1 = new LinkedHashSet<HotelFacilityResponseDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( HotelFacility hotelFacility : set ) {
            set1.add( hotelFacilityMapper.toResponseDTO( hotelFacility ) );
        }

        return set1;
    }

    protected HotelImageResponseDTO hotelImageToHotelImageResponseDTO(HotelImage hotelImage) {
        if ( hotelImage == null ) {
            return null;
        }

        HotelImageResponseDTO hotelImageResponseDTO = new HotelImageResponseDTO();

        hotelImageResponseDTO.setImageUrl( hotelImage.getImageUrl() );
        hotelImageResponseDTO.setMain( hotelImage.isMain() );

        return hotelImageResponseDTO;
    }

    protected List<HotelImageResponseDTO> hotelImageListToHotelImageResponseDTOList(List<HotelImage> list) {
        if ( list == null ) {
            return null;
        }

        List<HotelImageResponseDTO> list1 = new ArrayList<HotelImageResponseDTO>( list.size() );
        for ( HotelImage hotelImage : list ) {
            list1.add( hotelImageToHotelImageResponseDTO( hotelImage ) );
        }

        return list1;
    }

    protected List<RoomTypeResponseDTO> roomTypeListToRoomTypeResponseDTOList(List<RoomType> list) {
        if ( list == null ) {
            return null;
        }

        List<RoomTypeResponseDTO> list1 = new ArrayList<RoomTypeResponseDTO>( list.size() );
        for ( RoomType roomType : list ) {
            list1.add( roomTypeMapper.toResponseDTO( roomType ) );
        }

        return list1;
    }
}
