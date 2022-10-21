package com.example.slaugterhouse.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Package {
  private @Id @GeneratedValue Long packageId;
  @OneToMany
  private List<Tray> fromTrays;

  public Package() {
  }

  public Package(List<Tray> fromTrays) {
    this.fromTrays = fromTrays;
  }

  public Long getPackageId() {
    return packageId;
  }

  public void setPackageId(Long packageId) {
    this.packageId = packageId;
  }

  public List<Tray> getTrays() {
    return fromTrays;
  }

  public void setFromTrays(List<Tray> fromTrays) {
    this.fromTrays = fromTrays;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Package))
      return false;
    Package aPackage = (Package) o;
    return Objects.equals(packageId, aPackage.packageId) && Objects.equals(
        fromTrays, aPackage.fromTrays);
  }

  @Override public int hashCode() {
    return Objects.hash(packageId, fromTrays);
  }

  @Override public String toString() {
    return "Package{" + "packageId=" + packageId + ", fromTrays=" + fromTrays
        + '}';
  }
}
