package com.example.booking_platform.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignUpDTO {
    private String username;
    private String password;
    private String email;
}
