package com.example.booking_platform.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking_platform.dto.request.UserSignUpDTO;
import com.example.booking_platform.exception.AlreadyRegisteredException;
import com.example.booking_platform.model.Role;
import com.example.booking_platform.model.User;
import com.example.booking_platform.repository.RoleRepository;
import com.example.booking_platform.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public void register(UserSignUpDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new AlreadyRegisteredException("This username already taken");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyRegisteredException("This email is already registred");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("User role not found"));

        String password = passwordEncoder.encode(dto.getPassword());

        User user = new User(
                null,
                dto.getUsername(),
                password,
                dto.getEmail(),
                List.of(role));

        userRepository.save(user);
    }

}
