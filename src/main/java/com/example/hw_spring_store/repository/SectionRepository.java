package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.MainSection;
import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<MainSection, Long> {
  List<MainSection> findByActiveTrue();
  List<MainSection> findByActiveFalse();
}
