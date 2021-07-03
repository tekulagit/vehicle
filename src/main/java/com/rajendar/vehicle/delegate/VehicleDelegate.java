package com.rajendar.vehicle.delegate;

import com.rajendar.vehicle.VO.Person;
import com.rajendar.vehicle.entity.Vehicle;
import com.rajendar.vehicle.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class VehicleDelegate {

  @Autowired
  private VehicleService vehicleService;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  Environment environment;

  public boolean validateVehicleRegistration(String rego) {
    log.info("Inside validateVehicleRegistration method of VehicleDelegate");
    boolean vehicleRegistered = false;
    Vehicle vehicleDetails = vehicleService.findByVehicleRego(rego);
    if(null != vehicleDetails.getPersonid()){

      vehicleRegistered = true;
    }
    return vehicleRegistered;
  }

  public boolean checkPerson(Long personId) {
    log.info("Inside checkPerson method of VehicleDelegate");
    Person person = restTemplate
        .getForObject(environment.getProperty("spring.person.service.endpoint") + personId+"", Person.class);

      if(person != null && person.getPersonId() != null){
        log.info("Inside checkPerson method of VehicleDelegate - Person present with ID "+person.getPersonId());
        return true;
      }
    return false;
  }
}
