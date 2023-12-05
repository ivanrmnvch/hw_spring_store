package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.dto.OrderDto;
import com.example.hw_spring_store.dto.StatusDto;
import com.example.hw_spring_store.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrdersController {
  private final OrdersService ordersService;

  @PostMapping("/order")
  public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
    return ordersService.createOrder(orderDto);
  }

  @GetMapping("/order")
  public ResponseEntity<?> getOrders() {
   return ordersService.getOrders();
  }

  @GetMapping("/order/{id}")
  public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
    return ordersService.getOrderById(id);
  }

  @GetMapping(path = "/checkout",  produces = "application/json; charset=UTF-8")
  public ResponseEntity<?> checkout(@RequestParam(name = "ids") String idArray) {
    return ordersService.checkout(idArray);
  }

  @PutMapping("/order/{id}")
  public ResponseEntity<?> updateOrderStatus(
    @PathVariable("id") Long id,
    @RequestBody() StatusDto statusDto
  ) {
    return ordersService.updateOrderStatus(id, statusDto);
  }
}
