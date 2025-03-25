package org.product.controller;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String auth;

    @BeforeEach
    public void setUp() {
        auth = createBasicAuth("user", "password");
    }

    @Test
    public void should_return_token() throws Exception {
        mockMvc.perform(get("/login")
                .header("Authorization", auth))
            .andExpect(status().isOk())
            .andExpect(content().string(not(emptyString())));
    }

    private String createBasicAuth(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
}
