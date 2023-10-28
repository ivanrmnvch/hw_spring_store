package com.example.hw_spring_store.service;

import com.example.hw_spring_store.entities.Role;
import com.example.hw_spring_store.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepository;

  public Role getUserRole() {
    return roleRepository.findByName("ROLE_USER").get();
  }
}
