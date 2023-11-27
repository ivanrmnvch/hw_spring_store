package com.example.hw_spring_store.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class Resp {
  private int status;
  private String message;
  private Date timestamp;

  public Resp(int status, String message) {
    this.status = status;
    this.message = message;
    timestamp = new Date();
  }
}
