package com.example.hw_spring_store.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class Error {
  private int status;
  private String message;
  private Date timestamp;

  public Error(int status, String message) {
    this.status = status;
    this.message = message;
    timestamp = new Date();
  }
}
