package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.dto.BasketDto;
import com.example.hw_spring_store.service.BasketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BasketController {
  private final BasketService basketService;
  @GetMapping("/basket")
  public ResponseEntity<?> getBasket() throws JsonProcessingException {
    return basketService.getBasket();
  }

  @PostMapping("/basket/{id}")
  public ResponseEntity<?> addProductToBasket(
    @PathVariable("id") Long id,
    @RequestBody BasketDto basketDto
  ) {
    return basketService.addProductToBasket(id, basketDto);
  }

  @PutMapping("/basket/{id}")
  public ResponseEntity<?> updateProductCount(
    @PathVariable("id") Long id,
    @RequestBody BasketDto basketDto
  ) {
    return basketService.updateProductCount(id, basketDto);
  }

  @DeleteMapping("/basket/{id}")
  public ResponseEntity<?> deleteBasket(@PathVariable("id") Long id) {
    return basketService.deleteBasket(id);
  }
}
