package com.rajendar.vehicle.controller;

import com.rajendar.vehicle.delegate.VehicleDelegate;
import com.rajendar.vehicle.entity.Vehicle;
import com.rajendar.vehicle.service.VehicleService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revenueNSW")
@Slf4j
public class VehicleController {

    @Autowired
    private VehicleDelegate vehicleDelegate;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/vehicles/", method = RequestMethod.POST)
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle){
      log.info("Inside addVehicle method of VehicleController");
      boolean isPersonPresent = true;
      if(vehicle.getPersonid() != null) {
        isPersonPresent = vehicleDelegate.checkPerson(vehicle.getPersonid());
      }
      if(isPersonPresent) {
        Vehicle vehicleResponse = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<Vehicle>(vehicleResponse, HttpStatus.CREATED);
      }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person you are trying to register the vehicle for doesn't exist in data store "+vehicle.getPersonid());
      }

    }

    @RequestMapping(value = "/vehicles/{rego}", method = RequestMethod.PATCH)
    public ResponseEntity updateVehicle(@PathVariable(value = "rego") String rego, @RequestBody Vehicle vehicleRequest){
      log.info("Inside updateVehicle method of VehicleController");
      Vehicle vehicle = vehicleService.findByVehicleRego(rego);
      boolean isPersonPresent = true;
      boolean isVehicleRegistered = false;
      if(vehicle.getPersonid() != null) {
        isVehicleRegistered = vehicleDelegate.validateVehicleRegistration(vehicle.getRego());
      }
      if(vehicleRequest.getPersonid() != null){
        log.info("Inside updatePersonForVehicle method of VehicleController - personId "+vehicleRequest.getPersonid());
        isPersonPresent = vehicleDelegate.checkPerson(vehicleRequest.getPersonid());
      }
      if((vehicleRequest.getPersonid() == null) || (isPersonPresent && !isVehicleRegistered) ){
        vehicle.setPersonid(vehicleRequest.getPersonid());
        vehicleService.updatePersonForVehicle(vehicle);
      }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person not present in data store or vehicle already registered to person "+vehicle.getPersonid());
      }
      return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);

  }

  @RequestMapping(value = "/vehicles/", method = RequestMethod.GET)
  public ResponseEntity<List<Vehicle>> findVehicles(){
    log.info("Inside findVehicles method of VehicleController");
    List<Vehicle> vehiclesList = vehicleService.findVehicles();
    return new ResponseEntity<List<Vehicle>>(vehiclesList, HttpStatus.OK);
  }

    @RequestMapping(value = "/vehicles/{rego}", method = RequestMethod.GET)
    public ResponseEntity<Vehicle> findByVehicleRego(@PathVariable(value = "rego") String rego){
      log.info("Inside findByVehicleRego method of VehicleController");
      Vehicle vehicleResponse = vehicleService.findByVehicleRego(rego);
      return new ResponseEntity<Vehicle>(vehicleResponse, HttpStatus.OK);
    }
}
