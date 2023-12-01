package com.example.hw_spring_store.helpers;

import lombok.Data;

@Data
public class Pagination {
  private String[] list;
  private int totalPages;
  private long totalElements;

  public Pagination() {
    list = new String[]{"1"};
    totalPages = 1;
    totalElements = 0;
  }

  public Pagination(int totalPages, long totalElements) {
    list = new String[]{"1"};
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

//  public String[] getList() {
//
//  }
}
