package com.example.slaugterhouse.exceptions;

public class AnimalPartNotFoundException extends RuntimeException {
  public AnimalPartNotFoundException(Long id) {
    super("Could not find animal part with id: " + id);
  }
}
