package org.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.product.model.InventoryStatus;
import org.product.model.Product;
import org.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImp productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("product 1");
        product.setCategory("Cat 1");
        product.setInventoryStatus(InventoryStatus.INSTOCK);
    }

    @Test
    void should_create_product() {
        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.createProduct(product);

        assertNotNull(savedProduct);
        assertEquals("product 1", savedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void should_get_product_byId() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> foundProduct = productService.getProductByID(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("product 1", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void should_update_product_byId() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProductByID(1L, product);
        assertNotNull(updatedProduct);
        assertEquals("product 1", updatedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void should_delete_product_byId() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        productService.deleteProductByID(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
