package com.example.slaugterhouse.repository;

import com.example.slaugterhouse.model.AnimalPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalPartRepository extends JpaRepository<AnimalPart, Long> {
}
