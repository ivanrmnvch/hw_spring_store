package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.repository.ProductsRepository;
import com.example.hw_spring_store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StartPageController {
  private final ProductsService productsService;

  @RequestMapping("/")
  public String startPage(
    @RequestParam(name = "offset", defaultValue = "0") int offset,
    @RequestParam(name = "limit", defaultValue = "10") int limit,
    Model model
  ) {
    Page<ProductsEntity> productsEntity = productsService.getList(offset, limit);
    model.addAttribute("page", productsEntity);
    return "index";
  }
}