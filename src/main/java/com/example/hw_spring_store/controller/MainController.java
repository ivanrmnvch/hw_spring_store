package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class MainController {

  private static final Logger logger = Logger.getLogger(MainController.class.getName());
  private final ProductsService productService;

//  @GetMapping("/store")
//  public String store() {
//    //logger.info(">>>>>>>>> TEST");
//    Optional<ProductsEntity> productEntity = productService.getProductById((long)25);
//    return "<h1>Store</h1>";
//
//  }
//
//  @GetMapping("/edit")
//  public String edit() {
//    return "<h1>Edit</h1>";
//  }

  @GetMapping("/store")
  public String store() {
    return "store";
  }

  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }

  @GetMapping("/edit")
  public String edit() {
    return "edit";
  }

  @GetMapping("/info")
  public String info(Principal principal) {
    return principal.getName();
  }
}