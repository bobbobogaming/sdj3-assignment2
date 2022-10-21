package com.example.slaugterhouse.modelAssembler;

import com.example.slaugterhouse.controller.PackageController;
import com.example.slaugterhouse.controller.TrayController;
import com.example.slaugterhouse.model.Package;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PackageModelAssembler implements RepresentationModelAssembler<Package, EntityModel<Package>> {
  @Override public EntityModel<Package> toModel(Package entity) {
    EntityModel<Package> entityModel = EntityModel.of(entity,
        linkTo(methodOn(PackageController.class).one(entity.getPackageId())).withSelfRel(),
        linkTo(methodOn(PackageController.class).all()).withRel("packages"));


    if (!entity.getTrays().isEmpty()){
      for (int i = 0; i < entity.getTrays().size(); i++) {
        entityModel.add(linkTo(methodOn(TrayController.class).one(entity.getTrays().get(i).getId())).withRel("trayNo:" + (i + 1)));
      }
    }

    return entityModel;
  }
}
