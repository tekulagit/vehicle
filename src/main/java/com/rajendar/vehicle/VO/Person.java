package com.rajendar.vehicle.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private Long personId;
  private String firstName;
  private String lastName;
  private String address;

}
