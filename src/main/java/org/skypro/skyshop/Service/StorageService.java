package org.skypro.skyshop.Service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        fillStorageWithTestData();
    }

    private void fillStorageWithTestData() {

        productStorage.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Яблоко", 115));
        productStorage.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Груша", 130));
        productStorage.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Дыня", 95));
        productStorage.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Арбуз", 70));
        productStorage.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Слива", 145));
        productStorage.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Виноград", 125));

        productStorage.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Апельсин", 95, 5));
        productStorage.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Грейпфрукт", 105, 5));

        productStorage.put(UUID.randomUUID(), new FixPriceProduct(UUID.randomUUID(), "Масло"));


        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Дыня", "Сочная и вкусная"));
        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Арбуз", "Спелый и красный"));
        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Виноград", "Лучшие грозди"));
        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Масло", "Пастеризованное, лучшего отжима"));
    }


    public Collection<Article> getAllArticles() {
        return articleStorage.values();
    }


    public Collection<Product> getAllProducts() {
        return productStorage.values();
    }

    public Collection<Searchable> getAllSearchables() {
        return Stream.concat(
                productStorage.values().stream(),
                articleStorage.values().stream()
        ).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }
}

