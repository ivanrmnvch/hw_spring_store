package com.example.hw_spring_store.dto;

import com.example.hw_spring_store.entities.User;
import lombok.Data;

@Data
public class AuthorizedUserDto {
  private String token;
  private String login;

  public AuthorizedUserDto(String token, String login) {
    this.token = token;
    this.login = login;
  }
}