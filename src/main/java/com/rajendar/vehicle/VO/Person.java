package com.rajendar.vehicle.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private Long personid;
  private String firstname;
  private String lastname;
  private String address;

}
