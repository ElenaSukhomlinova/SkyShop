package org.skypro.skyshop.controller;

import org.skypro.skyshop.Service.BasketService;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/add/{id}")
    public void addToBasket(@PathVariable UUID id) {
        basketService.addProductToBasket(id);
    }

    @GetMapping
    public UserBasket getBasket() {
        return basketService.getUserBasket();
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}
