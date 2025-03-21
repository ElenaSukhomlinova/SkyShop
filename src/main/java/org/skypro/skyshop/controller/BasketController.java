package org.skypro.skyshop.controller;

import org.skypro.skyshop.Service.BasketService;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shop/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/{id}")
    public String addToBasket(@PathVariable UUID id) {
        basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping
    public UserBasket getBasket() {
        return basketService.getUserBasket();
    }

}
