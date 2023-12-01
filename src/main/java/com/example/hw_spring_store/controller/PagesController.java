package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.dto.ProductBodyDto;
import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PagesController {
  private final ProductsService productsService;

  @GetMapping("/products")
  public Page<ProductsEntity> getListOfProducts(
    @RequestParam(name = "offset", defaultValue = "0") int offset,
    @RequestParam(name = "limit", defaultValue = "10") int limit,
    @RequestParam(name = "name", defaultValue = "") String name,
    @RequestParam(name = "category", defaultValue = "") String category,
    @RequestParam(name = "brand", defaultValue = "") String brand,
    @RequestParam(name = "priceMin", defaultValue = "") Double priceMin,
    @RequestParam(name = "priceMax", defaultValue = "") Double priceMax
  ) {
    return productsService.getList(name, category, brand, priceMin, priceMax, offset, limit);
  }



  @GetMapping("/products/product/{id}")
  public Optional<ProductsEntity> getProduct(@PathVariable("id") Long id) {
    return productsService.getProductById(id);
  }

  @PostMapping("/products/product")
  public ResponseEntity<?> createProduct(@RequestBody ProductBodyDto productBodyDto) {
    return productsService.createProduct(productBodyDto);
  }
  @PutMapping("/products/product/{id}")
  public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductBodyDto updateBodyDto) {
    return productsService.updateProduct(id, updateBodyDto);
  }
}