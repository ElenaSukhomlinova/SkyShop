package org.skypro.skyshop.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.Searchable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {

    private StorageService storageService;

    @BeforeEach
    void setUp() {
        storageService = new StorageService();
    }

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        Collection<Product> products = storageService.getAllProducts();

        assertFalse(products.isEmpty());
        assertEquals(9,products.size());
    }

    @Test
    void getAllArticles_ShouldReturnAllArticles() {

        Collection<Article> articles = storageService.getAllArticles();


        assertFalse(articles.isEmpty());
        assertEquals(4, articles.size());
    }

    @Test
    void getAllSearchables_ShouldReturnProductsAndArticles() {

        Collection<Searchable> searchables = storageService.getAllSearchables();

        assertFalse(searchables.isEmpty());
        assertEquals(13, searchables.size());
    }

    @Test
    void getProductById_WhenProductNotExists_ShouldReturnEmpty() {

        UUID nonExistingId = UUID.randomUUID();

        Optional<Product> product = storageService.getProductById(nonExistingId);

        assertTrue(product.isEmpty());
    }
}
