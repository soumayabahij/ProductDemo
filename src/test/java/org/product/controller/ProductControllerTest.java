package org.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.product.model.InventoryStatus;
import org.product.model.Product;
import org.product.service.TokenGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenGeneratorService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private String validJwt;
    private Product product;
    private Authentication auth;

    @BeforeEach
    public void setUp() {
        auth = new UsernamePasswordAuthenticationToken("user", "password");
        validJwt = jwtService.generateToken(auth);
    }

    @Test
     void should_create_product() throws Exception {
        product = createProduct();
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
                .header("Authorization", "Bearer " + validJwt))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("PC"))
            .andExpect(jsonPath("$.price").value(88.0));
    }

    @Test
     void should_return_product_byId() throws Exception {
        Long id = getProductId();
        mockMvc.perform(get("/products/{id}", id)
                .header("Authorization", "Bearer " + validJwt))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("PC"));
    }

    @Test
     void should_update_product_byId() throws Exception {
        Long id = getProductId();
        Product updatedProduct = updateProduct();
        mockMvc.perform(put("/products/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct))
                .header("Authorization", "Bearer " + validJwt))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Mic"))
            .andExpect(jsonPath("$.code").value("Product B"));
    }

    @Test
     void should_delete_product_byId() throws Exception {
        Long id = getProductId();
        mockMvc.perform(delete("/products/{id}", id)
                .header("Authorization", "Bearer " + validJwt))
            .andExpect(status().isOk());
    }

    private Long getProductId() throws Exception {
        product = createProduct();
        MvcResult result = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
                .header("Authorization", "Bearer " + validJwt))
            .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        return jsonNode.get("id").asLong();
    }

    private Product createProduct() {
        return new Product("Cat A", "Product A", "i5 11gen", "pc.png", InventoryStatus.OUTOFSTOCK, "PC", BigDecimal.valueOf(88.0), 4, 7, 6);
    }

    private Product updateProduct() {
        return new Product("Cat B", "Product B", "11hz", "mic.png", InventoryStatus.INSTOCK, "Mic", BigDecimal.valueOf(50.3), 50, 8, 8);
    }
}