package com.rajendar.vehicle.util;

import com.rajendar.vehicle.entity.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class TestConstants {

  public static List<Vehicle> createPersonList() {
    List<Vehicle> vehicleList = new ArrayList<>();
    Vehicle v = new Vehicle("NS1234","BMW","5 Series",2016,4,1998,123L);
    Vehicle v1 = new Vehicle("NS4567","Kia","Rio",2016,4,1998,456L);
    Vehicle v2 = new Vehicle("NS7891","BMW","4 Series",2016,4,1998,789L);
    vehicleList.add(v);
    vehicleList.add(v1);
    vehicleList.add(v2);
    return vehicleList;
  }

}
