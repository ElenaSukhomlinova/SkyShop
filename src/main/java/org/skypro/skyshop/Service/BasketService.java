package org.skypro.skyshop.Service;

import org.skypro.skyshop.errors.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        System.out.println("Попытка добавить продукт с ID: " + id);
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isEmpty()) {
            System.out.println("Продукт с ID " + id + " не найден.");
            throw new NoSuchProductException("Товар с ID " + id + " не найден.");
        }
        productBasket.addProduct(id);
        System.out.println("Продукт с ID " + id + " успешно добавлен в корзину.");
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketItems = productBasket.getBasketItems();

        List<BasketItem> items = basketItems.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new NoSuchProductException("Товар с ID " + productId + " не найден."));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        return new UserBasket(items);
    }
    public int getProductQuantity(UUID id) {
        return productBasket.getBasketItems().getOrDefault(id, 0);
    }
}
