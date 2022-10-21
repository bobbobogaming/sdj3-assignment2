package com.example.slaugterhouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class AnimalPart {
  private @Id @GeneratedValue Long id;

  @ManyToOne
  private Animal animal;
  private AnimalPartType animalPartType;
  private int weight;

  public AnimalPart() {
  }

  public AnimalPart(Animal animal, AnimalPartType animalPartType, int weight) {
    this.animal = animal;
    this.animalPartType = animalPartType;
    this.weight = weight;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Animal getAnimal() {
    return animal;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public AnimalPartType getAnimalPartType() {
    return animalPartType;
  }

  public void setAnimalPartType(AnimalPartType animalPartType) {
    this.animalPartType = animalPartType;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof AnimalPart))
      return false;
    AnimalPart that = (AnimalPart) o;
    return weight == that.weight && Objects.equals(id, that.id)
        && Objects.equals(animal, that.animal) && Objects.equals(animalPartType,
        that.animalPartType);
  }

  @Override public int hashCode() {
    return Objects.hash(id, animal, animalPartType, weight);
  }

  @Override public String toString() {
    return "AnimalPart{" + "id=" + id + ", animal=" + animal + ", partType='"
        + animalPartType + '\'' + ", weight=" + weight + '}';
  }
}
