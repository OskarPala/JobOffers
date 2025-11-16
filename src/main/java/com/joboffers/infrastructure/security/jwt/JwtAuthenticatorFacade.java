package com.joboffers.infrastructure.security.jwt;

import com.joboffers.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import java.time.Clock;

@AllArgsConstructor
@Component
public class JwtAuthenticatorFacade {
    private final AuthenticationManager authenticationManager;
    private final Clock clock;
    private final JwtConfigurationProperties properties;



}
