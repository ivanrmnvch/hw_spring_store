package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.dto.SectionsDto;
import com.example.hw_spring_store.dto.SectionDto;
import com.example.hw_spring_store.entities.MainSection;
import com.example.hw_spring_store.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SectionController {
  private final SectionService sectionService;

  @GetMapping("/sections")
  public SectionsDto getCategories(
    @RequestParam(name = "active", defaultValue = "true") String active
  ) {
    return sectionService.getSections(Boolean.parseBoolean(active));
  }

  @GetMapping("/section/{id}")
  public Optional<MainSection> getMainSectionById(@PathVariable("id") Long id) {
    return sectionService.getMainSectionById(id);
  }

  @PostMapping("/section")
  public ResponseEntity<?> createSection(
   @RequestBody() SectionDto sectionDto
  ) {
    return sectionService.createSection(sectionDto);
  }

  @PutMapping("/section/{id}")
  public ResponseEntity<?> updateSection(
    @PathVariable("id") Long id,
    @RequestBody() SectionDto sectionDto
  ) {
    return sectionService.updateSection(id, sectionDto);
  }
}
