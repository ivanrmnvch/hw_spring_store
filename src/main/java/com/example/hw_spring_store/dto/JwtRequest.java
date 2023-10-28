package com.example.hw_spring_store.dto;

import lombok.Data;

@Data
public class JwtRequest {
  private String login;
  private String password;
}
