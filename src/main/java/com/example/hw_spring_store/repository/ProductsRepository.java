package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity, Long> {
  Page<ProductsEntity> findAll(Pageable pageable);
}
