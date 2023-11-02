package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @RequestMapping(value = "/store", method = RequestMethod.GET)
  public ResponseEntity<?> store()
  {
    System.out.println("TSTSTESTESTRFESfsfsdfd");

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Access-Control-Allow-Credentials", "true");
    headers.add("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, PUT, OPTIONS");
    headers.add("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    return ResponseEntity.ok().headers(headers).body("store");
  }

  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  public ResponseEntity<?> profile() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Access-Control-Allow-Credentials", "true");
    headers.add("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, PUT, OPTIONS");
    headers.add("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    return ResponseEntity.ok().headers(headers).body("profile");
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