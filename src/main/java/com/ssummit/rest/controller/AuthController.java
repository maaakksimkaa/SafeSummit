package com.ssummit.rest.controller;

import com.ssummit.dto.LoginDTO;
import com.ssummit.security.JwtTokenUtil;
import com.ssummit.service.UserService;
import com.ssummit.service.userDetails.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final String login = "admin";
    private final String password = "admin";

    public AuthController(CustomUserDetailsService authenticationService, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.customUserDetailsService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/authorization")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDto) {
        Map<String, Object> response = new HashMap<>();

        if (loginDto.getLogin().equals(login) && loginDto.getPassword().equals(password)) {
            UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDto.getLogin());
            String token = jwtTokenUtil.generateToken(foundUser);
            response.put("token", token);
            response.put("authorities", foundUser.getAuthorities());
            return ResponseEntity.ok().body(response);
        } else if(!userService.checkPassword(loginDto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user!\nWrongPassword");
        } else {
            UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDto.getLogin());
            String token = jwtTokenUtil.generateToken(foundUser);
            response.put("token", token);
            response.put("authorities", foundUser.getAuthorities());
            return ResponseEntity.ok().body(response);
        }
    }
}

