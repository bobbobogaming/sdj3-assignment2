package com.example.slaugterhouse.modelAssembler;

import com.example.slaugterhouse.controller.AnimalController;
import com.example.slaugterhouse.controller.AnimalPartController;
import com.example.slaugterhouse.model.AnimalPart;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnimalPartModelAssembler implements RepresentationModelAssembler<AnimalPart, EntityModel<AnimalPart>> {
  @Override public EntityModel<AnimalPart> toModel(AnimalPart entity) {
    EntityModel<AnimalPart> entityModel = EntityModel.of(entity,
        linkTo(methodOn(AnimalPartController.class).one(entity.getId())).withSelfRel(),
        linkTo(methodOn(AnimalPartController.class).all()).withRel("animalparts")

    );

    if (entity.getAnimal() != null){
      entityModel.add(linkTo(methodOn(AnimalController.class).one(entity.getAnimal().getRegistrationNo())).withRel("animal"));
    }

    return entityModel;
  }
}
