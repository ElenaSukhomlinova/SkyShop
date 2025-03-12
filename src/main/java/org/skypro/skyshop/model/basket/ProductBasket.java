package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductBasket {
    private final Map<UUID, Integer> basketItems;

    public ProductBasket() {
        this.basketItems = new HashMap<>();
    }

    public void addProduct(UUID id) {
        basketItems.put(id, basketItems.getOrDefault(id, 0) +1);
    }

    public Map<UUID, Integer> getBasketItems() {
        return Collections.unmodifiableMap(basketItems);
    }
}
