package com.example.booking_platform.service;

import com.example.booking_platform.exception.SpringMVCException;
import com.example.booking_platform.dto.UserResponseDTO;
import com.example.booking_platform.dto.UserSignInDTO;
import com.example.booking_platform.dto.UserSignUpDTO;
import com.example.booking_platform.model.User;
import com.example.booking_platform.repository.UserRepository;
import com.example.booking_platform.model.Role;
import com.example.booking_platform.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelmapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private static  Role role;
    public UserResponseDTO signIn(UserSignInDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new SpringMVCException("Invalid email or password"));


        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new SpringMVCException("Invalid email or password");
        }

        return modelmapper.map(user , UserResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           Optional<User> user = userRepository.findByUsername(username);

           return user.orElseThrow(() ->
                   new UsernameNotFoundException("User not found with email %s".formatted(username)));
    }

    @Transactional
    public void register(UserSignUpDTO dto) {

        if(role == null){
            role = roleRepository.findByName("USER").orElseThrow(() -> new SpringMVCException("Invalid role"));
        }

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new SpringMVCException("Email already in use");
        }

        String password = passwordEncoder.encode(dto.getPassword());
        User user = new User(null , dto.getUsername(), password, dto.getEmail() , List.of(role));
        userRepository.save(user);

    }
}
