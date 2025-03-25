package org.product.dto;

import java.util.UUID;

import org.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product create(ProductRequest productRequest) {
        return new Product.Builder()
            .withCode(productRequest.code())
            .withName(productRequest.name())
            .withDescription(productRequest.description())
            .withImage(productRequest.image())
            .withCategory(productRequest.category())
            .withPrice(productRequest.price())
            .withQuantity(productRequest.quantity())
            .withInventoryStatus(productRequest.inventoryStatus())
            .withRating(productRequest.rating())
            .withShellId(productRequest.shellId())
            .withInternalReference(UUID.randomUUID().toString())
            .build();
    }

    public Product update(ProductRequest productRequest) {
        return new Product.Builder()
            .withCode(productRequest.code())
            .withName(productRequest.name())
            .withDescription(productRequest.description())
            .withImage(productRequest.image())
            .withCategory(productRequest.category())
            .withPrice(productRequest.price())
            .withQuantity(productRequest.quantity())
            .withInventoryStatus(productRequest.inventoryStatus())
            .withRating(productRequest.rating())
            .withShellId(productRequest.shellId())
            .build();
    }
}
