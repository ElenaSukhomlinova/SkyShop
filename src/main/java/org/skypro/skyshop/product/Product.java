package org.skypro.skyshop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.UUID;


public abstract class Product implements Searchable {
    private final UUID id;
    private String productName;


    public Product(UUID id, String productName) {
        this.id = id;
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }

        this.productName = productName;

    }

    public String getProductName() {
        return productName;
    }

    public abstract int getProductCost();



    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return productName;
    }

    @JsonIgnore
    @Override
    public String getSearchContent() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return productName.hashCode();
    }

    @Override
    public UUID getID() {
        return id;
    }

}
