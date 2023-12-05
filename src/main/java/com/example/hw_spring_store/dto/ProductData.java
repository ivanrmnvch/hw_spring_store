package com.example.hw_spring_store.dto;

import com.example.hw_spring_store.entities.Brand;
import com.example.hw_spring_store.entities.SubSection;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
@Data
public class ProductData {
  public SubSection subSection;
  public Brand brand;
}
