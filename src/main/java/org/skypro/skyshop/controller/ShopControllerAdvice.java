package org.skypro.skyshop.controller;

import org.skypro.skyshop.errors.NoSuchProductException;
import org.skypro.skyshop.errors.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ShopControllerAdvice {
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        String errorCode = "PRODUCT_NOT_FOUND";
        String errorMessage = ex.getMessage();

        ShopError shopError = new ShopError(errorCode, errorMessage);
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
