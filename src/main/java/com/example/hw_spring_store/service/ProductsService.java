package com.example.hw_spring_store.service;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
  private final ProductsRepository productsRepository;

  public Page<ProductsEntity> getList(int offset, int limit) {
    System.out.println("LIMIT " + limit);
    System.out.println("OFFSET " + offset);
    PageRequest pageable = PageRequest.of(offset, limit);
    return productsRepository.findAll(pageable);
  }
  public Optional<ProductsEntity> getProductById(Long id) {
    return productsRepository.findById(id);
  }
}
