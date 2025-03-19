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

        UUID appleId = UUID.randomUUID();
        productStorage.put(appleId, new SimpleProduct(appleId, "Яблоко", 115));

        UUID pearId = UUID.randomUUID();
        productStorage.put(pearId, new SimpleProduct(pearId, "Груша", 130));

        UUID melonId = UUID.randomUUID();
        productStorage.put(melonId, new SimpleProduct(melonId, "Дыня", 95));

        UUID watermelonId = UUID.randomUUID();
        productStorage.put(watermelonId, new SimpleProduct(watermelonId, "Арбуз", 70));

        UUID plumId = UUID.randomUUID();
        productStorage.put(plumId, new SimpleProduct(plumId, "Слива", 145));

        UUID grapeId = UUID.randomUUID();
        productStorage.put(grapeId, new SimpleProduct(grapeId, "Виноград", 125));

        UUID orangeId = UUID.randomUUID();
        productStorage.put(orangeId, new DiscountedProduct(orangeId, "Апельсин", 95, 5));

        UUID grapefruitId = UUID.randomUUID();
        productStorage.put(grapefruitId, new DiscountedProduct(grapefruitId, "Грейпфрукт", 105, 5));

        UUID butterId = UUID.randomUUID();
        productStorage.put(butterId, new FixPriceProduct(butterId, "Масло"));


        UUID melonArticleId = UUID.randomUUID();
        articleStorage.put(melonArticleId, new Article(melonArticleId, "Дыня1", "Сочная и вкусная"));

        UUID watermelonArticleId = UUID.randomUUID();
        articleStorage.put(watermelonArticleId, new Article(watermelonArticleId, "Арбуз1", "Спелый и красный"));

        UUID grapeArticleId = UUID.randomUUID();
        articleStorage.put(grapeArticleId, new Article(grapeArticleId, "Виноград1", "Лучшие грозди"));

        UUID butterArticleId = UUID.randomUUID();
        articleStorage.put(butterArticleId, new Article(butterArticleId, "Масло1", "Пастеризованное, лучшего отжима"));
    }


    public Collection<Article> getAllArticles() {
        return Collections.unmodifiableCollection(articleStorage.values());
    }

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(productStorage.values());
    }

    public Collection<Searchable> getAllSearchables() {
        return Collections.unmodifiableCollection(Stream.concat(
                productStorage.values().stream(),
                articleStorage.values().stream()
        ).collect(Collectors.toList()));
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }
}

