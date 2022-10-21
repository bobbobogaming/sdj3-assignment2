package com.example.slaugterhouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Tray {
  private @Id @GeneratedValue Long id;
  private int maxWeight;
  private AnimalPartType animalPartType;
  @OneToMany
  private List<AnimalPart> animalParts;

  public Tray() {
  }

  public Tray(int maxWeight, AnimalPartType animalPartType,
      List<AnimalPart> animalParts) {
    this.maxWeight = maxWeight;
    this.animalPartType = animalPartType;
    this.animalParts = animalParts;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getMaxWeight() {
    return maxWeight;
  }

  public void setMaxWeight(int maxWeight) {
    this.maxWeight = maxWeight;
  }

  public AnimalPartType getAnimalPartType() {
    return animalPartType;
  }

  public void setAnimalPartType(AnimalPartType animalPartType) {
    this.animalPartType = animalPartType;
  }

  public List<AnimalPart> getAnimalParts() {
    return animalParts;
  }

  public void setAnimalParts(List<AnimalPart> animalParts) {
    this.animalParts = animalParts;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Tray))
      return false;
    Tray tray = (Tray) o;
    return maxWeight == tray.maxWeight && Objects.equals(id, tray.id)
        && Objects.equals(animalPartType, tray.animalPartType)
        && Objects.equals(animalParts, tray.animalParts);
  }

  @Override public int hashCode() {
    return Objects.hash(id, maxWeight, animalPartType, animalParts);
  }

  @Override public String toString() {
    return "Tray{" + "id=" + id + ", maxWeight=" + maxWeight
        + ", animalPartType='" + animalPartType + '\'' + ", animalParts="
        + animalParts + '}';
  }
}
