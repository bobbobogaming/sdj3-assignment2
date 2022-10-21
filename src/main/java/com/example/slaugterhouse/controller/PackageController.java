package com.example.slaugterhouse.controller;

import com.example.slaugterhouse.exceptions.PackageNotFoundException;
import com.example.slaugterhouse.model.Package;
import com.example.slaugterhouse.modelAssembler.PackageModelAssembler;
import com.example.slaugterhouse.repository.PackageRepository;
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
public class PackageController {
  private final PackageRepository repository;
  private final PackageModelAssembler assembler;

  public PackageController(PackageRepository repository, PackageModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @PostMapping("/packages") ResponseEntity<?> newPackage(@RequestBody Package aPackage){
    EntityModel<Package> entityModel = assembler.toModel(repository.save(aPackage));

    return  ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/packages")
  public CollectionModel<EntityModel<Package>> all() {
    List<EntityModel<Package>> packages = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(packages,
        linkTo(methodOn(TrayController.class).all()).withSelfRel());
  }

  @GetMapping("/packages/{id}")
  public EntityModel<Package> one(@PathVariable Long id) {
    Package aPackage = repository.findById(id)
        .orElseThrow(() -> new PackageNotFoundException(id));

    return assembler.toModel(aPackage);
  }
}
