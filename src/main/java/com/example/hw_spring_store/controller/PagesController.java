package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.helpers.Pagination;
import com.example.hw_spring_store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    Model model
  ) {
    return productsService.getList(offset, limit);
  }

  @GetMapping("/products/product/{id}")
  public Optional<ProductsEntity> getProduct(@PathVariable("id") Long id) {
    return productsService.getProductById(id);
  }
}