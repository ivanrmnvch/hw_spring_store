package com.example.hw_spring_store.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String name;
}
