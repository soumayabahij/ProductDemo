package org.product.controller;

import org.product.dto.ProductConverter;
import org.product.dto.ProductRequest;
import org.product.exception.ProductNotFoundException;
import org.product.model.InventoryStatus;
import org.product.model.Product;
import org.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Product API")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    public ProductController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productConverter.create(productRequest);
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @Operation(summary = "Get a Product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductByID(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Operation(summary = "Update a product by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody ProductRequest updatedProduct) {
        Product product = productConverter.update(updatedProduct);
        return ResponseEntity.ok(productService.updateProductByID(id, product));
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public void deleteProductByID(@PathVariable Long id) {
        productService.deleteProductByID(id);
    }

    @Operation(summary = "Get a Product by Inventory Status and Category")
    @GetMapping
    public ResponseEntity<Product> getProductByInventoryStatusAndCategory(
        @RequestParam InventoryStatus inventoryStatus,
        @RequestParam String category) {

        return productService.getProductByInventoryStatusAndCategory(inventoryStatus, category)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ProductNotFoundException("Product not found for the given inventory status and category."));
    }
}