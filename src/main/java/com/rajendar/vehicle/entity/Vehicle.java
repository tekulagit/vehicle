package com.rajendar.vehicle.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

  @Id
  private String rego;
  private String make;
  private String model;
  private int year;
  private int numwheels;
  private int milage;
  private Long personid;

}
