package com.example.hw_spring_store.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderDto {
  private List<Long> basketIds;
}
