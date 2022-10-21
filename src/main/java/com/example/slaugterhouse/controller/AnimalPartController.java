package com.example.slaugterhouse.controller;

import com.example.slaugterhouse.exceptions.AnimalPartNotFoundException;
import com.example.slaugterhouse.model.AnimalPart;
import com.example.slaugterhouse.modelAssembler.AnimalPartModelAssembler;
import com.example.slaugterhouse.repository.AnimalPartRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AnimalPartController {
  private final AnimalPartRepository repository;
  private final AnimalPartModelAssembler assembler;

  public AnimalPartController(AnimalPartRepository repository, AnimalPartModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @PostMapping("/animalparts") ResponseEntity<?> newAnimalPart(@RequestBody AnimalPart newAnimal){
    EntityModel<AnimalPart> entityModel = assembler.toModel(repository.save(newAnimal));

    return  ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/animalparts")
  public CollectionModel<EntityModel<AnimalPart>> all() {
    List<EntityModel<AnimalPart>> animalParts = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(animalParts,
        linkTo(methodOn(AnimalPartController.class).all()).withSelfRel());
  }

  @GetMapping("/animalparts/{id}")
  public EntityModel<AnimalPart> one(@PathVariable Long id) {
    AnimalPart animalPart = repository.findById(id)
        .orElseThrow(() -> new AnimalPartNotFoundException(id));

    return assembler.toModel(animalPart);
  }
}
