package com.example.booking_platform.mapper;

import com.example.booking_platform.dto.response.UserResponseDTO;
import com.example.booking_platform.model.Role;
import com.example.booking_platform.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T01:15:23+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUsername( user.getUsername() );
        userResponseDTO.setPassword( user.getPassword() );
        userResponseDTO.setEmail( user.getEmail() );
        List<Role> list = user.getRoles();
        if ( list != null ) {
            userResponseDTO.setRoles( new ArrayList<Role>( list ) );
        }

        return userResponseDTO;
    }
}
