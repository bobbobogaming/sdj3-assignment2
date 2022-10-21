package com.example.slaugterhouse.modelAssembler;

import com.example.slaugterhouse.controller.AnimalPartController;
import com.example.slaugterhouse.controller.TrayController;
import com.example.slaugterhouse.model.Tray;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TrayModelAssembler implements RepresentationModelAssembler<Tray, EntityModel<Tray>> {
  @Override public EntityModel<Tray> toModel(Tray entity) {
    EntityModel<Tray> entityModel = EntityModel.of(entity,
        linkTo(methodOn(TrayController.class).one(entity.getId())).withSelfRel(),
        linkTo(methodOn(TrayController.class).all()).withRel("trays"));

    if (!entity.getAnimalParts().isEmpty()){
      for (int i = 0; i < entity.getAnimalParts().size(); i++) {
        entityModel.add(linkTo(methodOn(AnimalPartController.class).one(entity.getAnimalParts().get(i).getId())).withRel("trayItem" + (i + 1)));
      }
    }

    return entityModel;
  }
}
