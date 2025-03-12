package org.skypro.skyshop.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private int basePrice;
    private int percentDiscount;

    public DiscountedProduct(UUID id, String productName, int basePrice, int percentDiscount) {
        super(id, productName);

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной или нулевой");
        }
        if (percentDiscount < 0 || percentDiscount > 100) {
            throw new IllegalArgumentException("Процент должен быть числом от 1 до 100");
        }
        this.basePrice = basePrice;
        this.percentDiscount = percentDiscount;
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    @Override
    public int getProductCost() {
        return basePrice - (basePrice * percentDiscount / 100);
    }

    @Override
    public String toString() {
        return String.format("Имя продукта со скидкой: %s, Стоимость: %d, (Скидка: %d%%)", getProductName(), getProductCost(), getPercentDiscount());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


}
