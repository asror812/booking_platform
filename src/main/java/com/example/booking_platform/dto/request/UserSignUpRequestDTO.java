package com.example.booking_platform.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignUpRequestDTO {
    private String username;
    private String password;
    private String email;
}
