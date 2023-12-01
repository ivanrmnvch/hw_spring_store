package com.example.hw_spring_store.dto;

import lombok.Data;

@Data
public class RefreshDto {
  private AuthorizedUserDto data;
  private String message;

  public RefreshDto(AuthorizedUserDto data, String message) {
    this.data = data;
    this.message = message;
  }
}
