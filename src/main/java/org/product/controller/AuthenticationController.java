package org.product.controller;

import org.product.service.TokenGeneratorService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication API")
public class AuthenticationController {

    private final TokenGeneratorService jwtService;

    public AuthenticationController(TokenGeneratorService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    @Operation(summary = "Authenticate a user")
    public String getToken(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
