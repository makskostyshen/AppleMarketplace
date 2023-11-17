package com.example.applemarketplace.port.rest.controller;

import com.example.applemarketplace.port.rest.dto.JwtResponseDto;
import com.example.applemarketplace.port.rest.dto.LoginRequestDto;
import com.example.applemarketplace.security.JwtParserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final JwtParserImpl jwtParser;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody final LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        String token = jwtParser.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
