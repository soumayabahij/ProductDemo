package org.product.dto;

import java.math.BigDecimal;

import org.product.model.InventoryStatus;

public record ProductRequest(
    String code,

    String name,

    String description,

    String image,

    String category,

    BigDecimal price,

    Integer quantity,

    Integer shellId,

    InventoryStatus inventoryStatus,

    Integer rating

) {
}
