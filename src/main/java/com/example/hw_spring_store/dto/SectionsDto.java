package com.example.hw_spring_store.dto;

import com.example.hw_spring_store.entities.MainSection;
import com.example.hw_spring_store.entities.SubSection;
import com.sun.tools.javac.Main;
import lombok.Data;

import java.util.List;

@Data
public class SectionsDto {
  private List<MainSection> mainSections;
  private List<SubSection> subSections;

  public SectionsDto(List<MainSection> mainSections, List<SubSection> subSections) {
    this.mainSections = mainSections;
    this.subSections = subSections;
  }
}
