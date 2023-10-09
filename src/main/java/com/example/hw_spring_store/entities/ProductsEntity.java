package com.example.hw_spring_store.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "products")
public class ProductsEntity{
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private int price;
  @Column(nullable = false)
  private String imagePath;
  @Column(nullable = false)
  private String brand;
  @Column(nullable = false)
  private String category;
  @Column(columnDefinition = "boolean DEFAULT TRUE", nullable = false)
  private boolean active;
  @Column(nullable = false)
  private LocalDateTime updatedAt;
  @Column(nullable = false)
  private LocalDateTime createdAt;
}