package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity, Long> {
  Page<ProductsEntity> findAll(Specification<ProductsEntity> spec, Pageable pageable);

  List<ProductsEntity> findByIdIn(List<Long> productIds);
}
