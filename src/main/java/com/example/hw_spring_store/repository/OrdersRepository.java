package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
  List<Order> findByUserId(Long userId);

  @Query("select count(distinct o.orderId) as count from Order o")
  Long countDistinctOrderId();
}
