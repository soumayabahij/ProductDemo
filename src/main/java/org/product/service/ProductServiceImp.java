package org.product.service;

import java.util.Optional;

import org.product.exception.ProductNotFoundException;
import org.product.model.InventoryStatus;
import org.product.model.Product;
import org.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductByID(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProductByID(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        existingProduct.setCode(updatedProduct.getCode());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setRating(updatedProduct.getRating());
        existingProduct.setShellId(updatedProduct.getShellId());
        existingProduct.setInventoryStatus(updatedProduct.getInventoryStatus());

        return productRepository.save(existingProduct);
    }

    public void deleteProductByID(Long id) {
        Optional<Product> savedProduct = productRepository.findById(id);
        if (savedProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }

    public Optional<Product> getProductByInventoryStatusAndCategory(InventoryStatus inventoryStatus, String category) {
        return productRepository.findProductByCategoryAndInventoryStatus(inventoryStatus, category);
    }
}
