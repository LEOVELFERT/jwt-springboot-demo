package com.leo.jwt_springboot_demo.controller;

import com.leo.jwt_springboot_demo.configuration.LoginForm;
import com.leo.jwt_springboot_demo.service.JwtService;
import com.leo.jwt_springboot_demo.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private static final Logger log = LoggerFactory.getLogger(ContentController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/home")
    public String handleHome(){
        return "Welcome to the home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome(){
        return "Welcome to the Admin Home";
    }

    @GetMapping("/user/home")
    public String handleUserHome(){
        return "Welcome To the User Home";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginForm requestDto){
        log.info("Entered into the authenticated users-------------->");
        String userName=requestDto.userName();
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        userName, requestDto.password()
                ));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(userName));
        }else {
            throw new UsernameNotFoundException("Invalid credentials....");
        }
    }


    @GetMapping("/enodePassword")
    public String encodedPassword(){
        return passwordEncoder.encode("password");
    }
}
