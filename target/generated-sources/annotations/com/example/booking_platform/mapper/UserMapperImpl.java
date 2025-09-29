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
    date = "2025-09-28T16:17:28+0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setPassword( user.getPassword() );
        List<Role> list = user.getRoles();
        if ( list != null ) {
            userResponseDTO.setRoles( new ArrayList<Role>( list ) );
        }
        userResponseDTO.setUsername( user.getUsername() );

        return userResponseDTO;
    }
}
