package org.skypro.skyshop.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.Service.BasketService;
import org.skypro.skyshop.Service.StorageService;
import org.skypro.skyshop.errors.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.product.Product;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addToBasket_WhenProductNotExists_ShouldThrowException() {
        UUID nonExistentId = UUID.randomUUID();
        when(storageService.getProductById(nonExistentId)).thenReturn(Optional.empty());

        // Изменяем ожидаемое исключение на NoSuchProductException
        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(nonExistentId);
        });

        verify(storageService).getProductById(nonExistentId);
        verifyNoInteractions(productBasket);
    }

    @Test
    void addToBasket_WhenProductExists_ShouldCallAddProduct() {
        UUID existentId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(existentId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(existentId);

        verify(storageService).getProductById(existentId);
        verify(productBasket).addProduct(existentId);
    }

    @Test
    void addToBasket_ShouldIncrementQuantityForExistingProduct() {
        UUID productId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(productId);

        basketService.addProductToBasket(productId);

        verify(storageService, times(2)).getProductById(productId);
        verify(productBasket, times(2)).addProduct(productId);
    }
}