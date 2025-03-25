package org.product.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductManagement")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "internal_reference")
    private String internalReference;

    @Column(name = "shell_id")
    private Integer shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    @Column(name = "rating")
    private Integer rating;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(String category,
                   String code,
                   String description,
                   String image,
                   InventoryStatus inventoryStatus,
                   String name,
                   BigDecimal price,
                   Integer quantity,
                   Integer rating,
                   Integer shellId) {
        this.category = category;
        this.code = code;
        this.description = description;
        this.image = image;
        this.inventoryStatus = inventoryStatus;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
        this.shellId = shellId;
    }

    public Product(Builder builder) {
        this.code = builder.code;
        this.name = builder.name;
        this.description = builder.description;
        this.image = builder.image;
        this.category = builder.category;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.shellId = builder.shellId;
        this.inventoryStatus = builder.inventoryStatus;
        this.rating = builder.rating;
        this.internalReference = builder.internalReference;
    }

    public static class Builder {
        private String code;
        private String name;
        private String description;
        private String image;
        private String category;
        private BigDecimal price;
        private Integer quantity;
        private Integer shellId;
        private InventoryStatus inventoryStatus;
        private Integer rating;
        private String internalReference;

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withShellId(Integer shellId) {
            this.shellId = shellId;
            return this;
        }

        public Builder withInventoryStatus(InventoryStatus inventoryStatus) {
            this.inventoryStatus = inventoryStatus;
            return this;
        }

        public Builder withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder withInternalReference(String internalReference) {
            this.internalReference = internalReference;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public String getCategory() {
        return category;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getShellId() {
        return shellId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public Long getId() {
        return id;
    }

    public void setShellId(Integer shellId) {
        this.shellId = shellId;
    }
}
