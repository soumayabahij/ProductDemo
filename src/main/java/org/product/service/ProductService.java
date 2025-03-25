package org.product.service;

import java.util.Optional;

import org.product.model.InventoryStatus;
import org.product.model.Product;

public interface ProductService {

    Product createProduct(Product product);

    Optional<Product> getProductByID(Long id);

    Product updateProductByID(Long id, Product updatedProduct);

    void deleteProductByID(Long id);

    Optional<Product> getProductByInventoryStatusAndCategory(InventoryStatus inventoryStatus, String category);
}
