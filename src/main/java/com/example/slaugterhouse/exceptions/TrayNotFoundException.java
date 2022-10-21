package com.example.slaugterhouse.exceptions;

public class TrayNotFoundException extends RuntimeException {
  public TrayNotFoundException(Long id) {
    super("Could not find tray: " + id);
  }
}
