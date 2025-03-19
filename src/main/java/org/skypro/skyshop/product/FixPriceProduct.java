package org.skypro.skyshop.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 120;
    private String productName;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName); // Вызов конструктора родительского класса
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public int getProductCost() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return String.format("Имя продукта с фиксированной ценой: %s, Фиксированная цена: %d",
                getProductName(), getProductCost());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}