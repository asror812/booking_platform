package com.example.booking_platform.user;

import com.example.booking_platform.user.dto.UserResponseDTO;
import com.example.booking_platform.user.dto.UserSignInDTO;
import com.example.booking_platform.user.dto.UserSignUpDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
//x@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    @GetMapping("/login")
    public String getSignInPage(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String getSignUpPage(){
        return "auth/register";
    }

    @PostMapping("/login")
    public String signIn(@Valid  @ModelAttribute UserSignInDTO dto  , Model model){

        System.out.println(dto.getPassword());
        System.out.println(dto.getUsername());
        UserResponseDTO responseDTO = userService.signIn(dto);
        model.addAttribute("user", responseDTO);

        return "index";
    }


    @PostMapping("/register")
    public String signUp(@ModelAttribute @Valid UserSignUpDTO dto){

        log.warn("DTO info {} -> {} -> {}" , dto.getPassword() , dto.getPassword() , dto.getUsername());
        userService.register(dto);
        return "auth/login";
    }
}
