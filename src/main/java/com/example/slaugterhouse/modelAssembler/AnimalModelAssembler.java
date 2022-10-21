package com.example.slaugterhouse.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.slaugterhouse.controller.AnimalController;
import com.example.slaugterhouse.model.Animal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AnimalModelAssembler implements RepresentationModelAssembler<Animal,EntityModel<Animal>>{

  @Override public EntityModel<Animal> toModel(Animal entity) {
    return EntityModel.of(entity,
        linkTo(methodOn(AnimalController.class).one(entity.getRegistrationNo())).withSelfRel(),
        linkTo(methodOn(AnimalController.class).all(null, null)).withRel("animals").expand());
  }
}
