package com.example.hw_spring_store.repository;

import com.example.hw_spring_store.entities.MainSection;
import com.example.hw_spring_store.entities.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSectionsRepository extends JpaRepository<SubSection, Long> {}
