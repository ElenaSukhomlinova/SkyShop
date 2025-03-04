package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private int productPrice;

    public SimpleProduct(UUID id, String productName, int productPrice) {
        super(id, productName);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной или нулевой");
        }
        this.productPrice = productPrice;
    }

    @Override
    public int getProductCost() {
        return productPrice;
    }

    @Override
    public String toString() {
        return String.format("Имя продукта: %s, Стоимость: %d", getProductName(), getProductCost());
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

}
