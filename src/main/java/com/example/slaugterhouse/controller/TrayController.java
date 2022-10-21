package com.example.slaugterhouse.controller;

import com.example.slaugterhouse.exceptions.TrayNotFoundException;
import com.example.slaugterhouse.model.Tray;
import com.example.slaugterhouse.modelAssembler.TrayModelAssembler;
import com.example.slaugterhouse.repository.TrayRepository;
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
public class TrayController {
  private final TrayRepository repository;
  private final TrayModelAssembler assembler;

  public TrayController(TrayRepository repository, TrayModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @PostMapping("/trays") ResponseEntity<?> newTray(@RequestBody Tray tray){
    EntityModel<Tray> entityModel = assembler.toModel(repository.save(tray));

    return  ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/trays")
  public CollectionModel<EntityModel<Tray>> all() {
    List<EntityModel<Tray>> tray = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(tray,
        linkTo(methodOn(TrayController.class).all()).withSelfRel());
  }

  @GetMapping("/trays/{id}")
  public EntityModel<Tray> one(@PathVariable Long id) {
    Tray tray = repository.findById(id)
        .orElseThrow(() -> new TrayNotFoundException(id));

    return assembler.toModel(tray);
  }
}
