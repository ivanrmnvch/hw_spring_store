package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity, Long> {
//  @Query(value =
//    "SELECT " +
//      "COUNT (*) OVER () as count," +
//      "id," +
//      "name," +
//      "description," +
//      "price," +
//      "image_path," +
//      "brand," +
//      "category," +
//      "active," +
//      "created_at," +
//      "updated_at " +
//      "FROM products " +
//    "WHERE active IS TRUE " +
//    "ORDER BY id ASC "
////    "LIMIT ?2 " +
////    "OFFSET ?3;",
//    ,nativeQuery = true)
//  Optional<List<ProductsEntity>> getList(String where);

  Page<ProductsEntity> findAll(Pageable pageable);
}
