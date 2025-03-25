package org.product.repository;

import java.util.Optional;

import org.product.model.InventoryStatus;
import org.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByCategoryAndInventoryStatus(InventoryStatus inventoryStatus, String category);
}