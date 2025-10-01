package com.example.booking_platform.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking_platform.dto.request.UserSignUpRequestDTO;
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
    public void register(UserSignUpRequestDTO dto) {
        checkCredentials(dto.getUsername(), dto.getEmail());

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

    private void checkCredentials(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyRegisteredException("username");
        }

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyRegisteredException("email");
        }
    }

}
