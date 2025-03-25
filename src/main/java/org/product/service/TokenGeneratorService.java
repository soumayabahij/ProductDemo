package org.product.service;

import org.springframework.security.core.Authentication;

public interface TokenGeneratorService {

    String generateToken(Authentication authentication);
}
