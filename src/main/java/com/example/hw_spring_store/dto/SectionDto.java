package com.example.hw_spring_store.dto;

import lombok.Data;
@Data
public class SectionDto {
  private String name;
  private Boolean active;
  private String type;
  private Long mainSectionId;
}
