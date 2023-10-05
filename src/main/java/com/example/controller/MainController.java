package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/store")
  public String store() {
    return "<h1>Store</h1>";
  }

  @GetMapping("/profile")
  public String profile() {
    return "<h1>Profile</h1>";
  }

  @GetMapping("/edit")
  public String edit() {
    return "<h1>Edit</h1>";
  }
}