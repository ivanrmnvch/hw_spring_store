package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.entities.Brand;
import com.example.hw_spring_store.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {
  private final BrandService brandService;

  @GetMapping("/brands")
  public List<Brand> getBrandList() {
    return brandService.getBrandList();
  }
}
