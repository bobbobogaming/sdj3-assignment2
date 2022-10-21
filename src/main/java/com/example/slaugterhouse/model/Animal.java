package com.example.slaugterhouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Animal {
  private @Id @GeneratedValue Long registrationNo;
  private AnimalType animalType;
  private int weight;
  private String origin;
  private LocalDateTime arrival;

  public Animal() {}

  public Animal( AnimalType animalType, int weight, String origin) {
    this.animalType = animalType;
    this.weight = weight;
    this.origin = origin;
    this.arrival = LocalDateTime.now();
  }

  public Long getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(Long registrationNo) {
    this.registrationNo = registrationNo;
  }

  public AnimalType getAnimalType() {
    return animalType;
  }

  public void setAnimalType(AnimalType animalType) {
    this.animalType = animalType;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public LocalDateTime getArrival() {
    return arrival;
  }

  public void setArrival(String arrival) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    this.arrival = LocalDateTime.parse(arrival, formatter);
  }

  @Override public int hashCode() {
    return Objects.hash(registrationNo,animalType,weight,origin,animalType);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Animal))
      return false;
    Animal other = (Animal) obj;
    return registrationNo.equals(other.registrationNo)
        && animalType == other.animalType
        && weight == other.weight
        && origin.equals(other.origin)
        && arrival.equals(other.arrival);
  }

  @Override public String toString() {
    return "Animal{" + "registrationNo=" + registrationNo + ", animalType="
        + animalType + ", weight=" + weight + ", origin='" + origin + '\''
        + ", arrival=" + arrival + '}';
  }
}
