package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
  List<Basket> findByUserId(Long userId);
  List<Basket> findByIdIn(List<Long> basketIds);
  Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);
}

