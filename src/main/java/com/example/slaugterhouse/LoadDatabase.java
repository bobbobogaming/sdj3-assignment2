package com.example.slaugterhouse;

import com.example.slaugterhouse.model.*;
import com.example.slaugterhouse.model.Package;
import com.example.slaugterhouse.repository.AnimalPartRepository;
import com.example.slaugterhouse.repository.AnimalRepository;
import com.example.slaugterhouse.repository.PackageRepository;
import com.example.slaugterhouse.repository.TrayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(AnimalRepository animalRepository, AnimalPartRepository animalPartRepository,
      TrayRepository trayRepository, PackageRepository packageRepository) {

    return args -> {
      Animal animal1 = new Animal(AnimalType.CHICKEN, 7000, "Horsens");
      animalRepository.save(animal1);
      animalRepository.save(new Animal(AnimalType.COW, 410200, "Vejle"));
      animalRepository.save(new Animal(AnimalType.PIG, 50500, "Kolding"));
      animalRepository.save(new Animal(AnimalType.COW, 470900, "Horsens"));
      animalRepository.save(new Animal(AnimalType.PIG, 40900, "Vejle"));

      AnimalPart animalPart1 = new AnimalPart(animal1, AnimalPartType.LIVER,30);
      animalPartRepository.save(animalPart1);

      Tray tray = new Tray(1000,AnimalPartType.LIVER,List.of(new AnimalPart[] {animalPart1}));
      trayRepository.save(tray);

      packageRepository.save(new Package(List.of(new Tray[]{tray})));

      animalRepository.findAll().forEach(animal -> log.info("Preloaded " + animal));

    };
  }
}
