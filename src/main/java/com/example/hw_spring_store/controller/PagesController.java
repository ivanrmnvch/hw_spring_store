package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.helpers.Pagination;
import com.example.hw_spring_store.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PagesController {
  private final ProductsService productsService;

  @RequestMapping("/")
  public String startPage(
    @RequestParam(name = "offset", defaultValue = "0") int offset,
    @RequestParam(name = "limit", defaultValue = "10") int limit,
    Model model
  ) {
    Page<ProductsEntity> productsEntity = productsService.getList(offset, limit);
    model.addAttribute("page", productsEntity);
    Pagination pagination = new Pagination(
      productsEntity.getTotalPages(),
      productsEntity.getTotalElements()
    );
    model.addAttribute("pagination", pagination);
    return "index";
  }

  @RequestMapping("/profile")
  public String profile() {
    return "profile";
  }

  @RequestMapping("/edit/{id}")
  public String edit(
    @PathVariable("id") Long id
  ) {
    System.out.println("ID " + id);
    return "edit_page";
  }
}