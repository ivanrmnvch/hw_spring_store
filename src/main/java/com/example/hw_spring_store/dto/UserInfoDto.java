package com.example.hw_spring_store.dto;

import lombok.Data;

@Data
public class UserInfoDto {
  private String login;

  public UserInfoDto(String login) {
    this.login = login;
  }
}
