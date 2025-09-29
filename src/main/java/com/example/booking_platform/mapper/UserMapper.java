package com.example.booking_platform.mapper;

import org.mapstruct.Mapper;

import com.example.booking_platform.dto.response.UserResponseDTO;
import com.example.booking_platform.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);
}