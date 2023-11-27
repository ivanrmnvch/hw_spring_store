package com.example.hw_spring_store.service;

import com.example.hw_spring_store.dto.OrderDto;
import com.example.hw_spring_store.entities.Basket;
import com.example.hw_spring_store.entities.Order;
import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.entities.User;
import com.example.hw_spring_store.exceptions.Resp;
import com.example.hw_spring_store.repository.BasketRepository;
import com.example.hw_spring_store.repository.OrdersRepository;
import com.example.hw_spring_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {
  private final UserRepository userRepository;
  private final OrdersRepository ordersRepository;
  private final BasketRepository basketRepository;
  public ResponseEntity<?> createOrder(OrderDto orderDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String login = authentication.getName();

    if (login == null) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Необходимо пройте авторизацию"), HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userRepository.findByLogin(login);

    if (user.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Пользователь не найден"), HttpStatus.BAD_REQUEST);
    }

    Long orderId = ordersRepository.countDistinctOrderId() + 1;

    List<Basket> basketList = basketRepository.findByIdIn(orderDto.getBasketIds());

    basketRepository.deleteAll(basketList);

    List<Order> orders = new ArrayList<>();

    LocalDateTime now = LocalDateTime.now();

    for (Basket basket : basketList) {
      ProductsEntity product = basket.getProduct();
      int count = basket.getCount();

      Order order = new Order();
      order.setOrderId(orderId);
      order.setUser(user.get());
      order.setProduct(product);
      order.setCount(count);
      order.setStatus("created");
      order.setCreatedAt(now);
      order.setUpdatedAt(now);
      orders.add(order);
    }

    List<Long> basketIds = basketList.stream().map(Basket::getId).toList();

    ordersRepository.saveAll(orders);
    // basketRepository.deleteAll(basketIds);

    return new ResponseEntity<>(new Resp(HttpStatus.OK.value(), "Заказ успешно создан"), HttpStatus.OK);
  }

  public ResponseEntity<?> getOrders() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String login = authentication.getName();

    if (login == null) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Необходимо пройте авторизацию"), HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userRepository.findByLogin(login);

    if (user.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Пользователь не найден"), HttpStatus.BAD_REQUEST);
    }

    List<Order> orders = ordersRepository.findByUserId(user.get().getId());

    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  public ResponseEntity<?> checkout(String idArray) {
    if (Objects.equals(idArray, "")) {
      return new ResponseEntity<>(new String[0], HttpStatus.OK);
    }
    String[] basketIds = idArray.split(", ");

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String login = authentication.getName();

    if (login == null) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Необходимо пройте авторизацию"), HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userRepository.findByLogin(login);

    if (user.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Пользователь не найден"), HttpStatus.BAD_REQUEST);
    }
    List<Long> ids = Arrays.stream(basketIds).map(Long::parseLong).toList();
    System.out.println("parse long" + ids);
    List<Basket> basketList = basketRepository.findByIdIn(ids);

    return new ResponseEntity<>(basketList, HttpStatus.OK);
  }
}
