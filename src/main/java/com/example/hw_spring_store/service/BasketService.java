package com.example.hw_spring_store.service;

import com.example.hw_spring_store.dto.BasketDto;
import com.example.hw_spring_store.dto.UserDto;
import com.example.hw_spring_store.entities.Basket;
import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.entities.User;
import com.example.hw_spring_store.exceptions.Resp;
import com.example.hw_spring_store.repository.BasketRepository;
import com.example.hw_spring_store.repository.ProductsRepository;
import com.example.hw_spring_store.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
  private final BasketRepository basketRepository;
  private final UserRepository userRepository;
  private final ProductsRepository productsRepository;
  public ResponseEntity<?> getBasket() throws JsonProcessingException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String login = authentication.getName();
    System.out.println("LOGIN " + login);

    if (login == null) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Необходимо пройте авторизацию"), HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userRepository.findByLogin(login);

    if (user.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Пользователь не найден"), HttpStatus.BAD_REQUEST);
    }

    List<Basket> basketList = basketRepository.findByUserId(user.get().getId());

    return new ResponseEntity<>(basketList, HttpStatus.OK);
  }

  public ResponseEntity<?> addProductToBasket(Long id, BasketDto basketDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String login = authentication.getName();
    System.out.println("LOGIN " + login);

    if (login == null) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Необходимо пройте авторизацию"), HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userRepository.findByLogin(login);

    if (user.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Пользователь не найден"), HttpStatus.BAD_REQUEST);
    }

    Optional<ProductsEntity> product = productsRepository.findById(id);

    if (product.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Не верный id заказа"), HttpStatus.BAD_REQUEST);
    }

    System.out.println("user id" + user.get().getId());
    System.out.println("product id" + product.get().getId());

    Optional<Basket> basket = basketRepository.findByUserIdAndProductId(user.get().getId(), product.get().getId());

    if (basket.isPresent()) {
      Basket updateBasket = basket.get();
      updateBasket.setCount(updateBasket.getCount() + basketDto.getCount());
      basketRepository.save(updateBasket);
      return new ResponseEntity<>(new Resp(HttpStatus.CREATED.value(), "Количество товара успешно обновлено"), HttpStatus.CREATED);
    }

    Basket newBasket = new Basket();
    newBasket.setProduct(product.get());
    newBasket.setUser(user.get());

    LocalDateTime now = LocalDateTime.now();

    newBasket.setCreatedAt(now);
    newBasket.setUpdatedAt(now);
    newBasket.setActive(true);
    newBasket.setCount(basketDto.getCount());
    basketRepository.save(newBasket);
    return new ResponseEntity<>(new Resp(HttpStatus.CREATED.value(), "Товар успешно добавлен в корзину"), HttpStatus.CREATED);
  }

  public ResponseEntity<?> updateProductCount(Long id, BasketDto basketDto) {
    Optional<Basket> basket = basketRepository.findById(id);
    if (basket.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Не верный id заказа"), HttpStatus.BAD_REQUEST);
    }
    Basket updatedBasket = basket.get();
    updatedBasket.setCount(basketDto.getCount());
    LocalDateTime now = LocalDateTime.now();
    updatedBasket.setUpdatedAt(now);
    basketRepository.save(updatedBasket);
    return new ResponseEntity<>(new Resp(HttpStatus.OK.value(), "Количестов товара успешно обновлено"), HttpStatus.OK);
  }

  public ResponseEntity<?> deleteBasket(Long id) {
//    Optional<Basket> basket = basketRepository.findById(id);
//    if (basket.isEmpty()) {
//      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Не верный id заказа"), HttpStatus.BAD_REQUEST);
//    }
//    Basket updatedBasket = basket.get();
//    updatedBasket.setActive(false);
//    basketRepository.save(updatedBasket);
    basketRepository.deleteById(id);
    return new ResponseEntity<>(new Resp(HttpStatus.OK.value(), "Товар успешно удален из корзины"), HttpStatus.OK);
  }
}
