package com.example.hw_spring_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String login;
  private String email;
}
