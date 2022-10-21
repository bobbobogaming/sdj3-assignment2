package com.example.slaugterhouse.repository;

import com.example.slaugterhouse.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
