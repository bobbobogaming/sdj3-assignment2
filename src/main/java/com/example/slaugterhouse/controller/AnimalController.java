package com.example.slaugterhouse.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.slaugterhouse.exceptions.AnimalNotFoundException;
import com.example.slaugterhouse.model.Animal;
import com.example.slaugterhouse.modelAssembler.AnimalModelAssembler;
import com.example.slaugterhouse.repository.AnimalRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnimalController {

  private final AnimalRepository repository;
  private final AnimalModelAssembler assembler;

  public AnimalController(AnimalRepository repository, AnimalModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @PostMapping("/animals") ResponseEntity<?> newAnimal(@RequestBody Animal newAnimal){
    EntityModel<Animal> entityModel = assembler.toModel(repository.save(newAnimal));

    return  ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/animals")
  public CollectionModel<EntityModel<Animal>> all(@RequestParam(required = false) String origin, @RequestParam(required = false) String date) {
    List<EntityModel<Animal>> animals = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    if (origin != null) {
      for (int i = animals.size()-1; i >= 0; i--) {
        if (!origin.equalsIgnoreCase(animals.get(i).getContent().getOrigin()))
          animals.remove(i);
      }
    }

    if (date != null) {
      date += " 00:00";

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      LocalDateTime parsed = LocalDateTime.parse(date,formatter);

      for (int i = animals.size()-1;i>=0; i--) {
        LocalDateTime localDate = animals.get(i).getContent().getArrival();
       if (localDate == null || !(localDate.getYear() == parsed.getYear()
          && localDate.getMonth().equals(parsed.getMonth())
          && localDate.getDayOfMonth() == parsed.getDayOfMonth()))
         animals.remove(i);
      }
    }

    return CollectionModel.of(animals,
        linkTo(methodOn(AnimalController.class).all(null,null)).withSelfRel().expand());
  }

  @GetMapping("/animals/{id}")
  public EntityModel<Animal> one(@PathVariable Long id) {
    Animal animal = repository.findById(id)
        .orElseThrow(() -> new AnimalNotFoundException(id));

    return assembler.toModel(animal);
  }
}
