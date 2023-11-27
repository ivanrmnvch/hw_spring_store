package com.example.hw_spring_store.service;

import com.example.hw_spring_store.entities.Brand;
import com.example.hw_spring_store.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepository;

  public List<Brand> getBrandList() {
    return brandRepository.findAll();
  }

  public Optional<Brand> getBrandById(Long id) {
    return brandRepository.findById(id);
  }
}
