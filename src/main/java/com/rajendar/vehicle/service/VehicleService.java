package com.rajendar.vehicle.service;

import com.rajendar.vehicle.entity.Vehicle;
import com.rajendar.vehicle.repository.VehicleRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  public Vehicle addVehicle(Vehicle vehicle) {
    log.info("Inside addVehicle method of VehicleService");
    return vehicleRepository.save(vehicle);
  }

  public Vehicle findByVehicleRego(String rego) {
    log.info("Inside findByVehicleRego method of VehicleService");
    return vehicleRepository.findByRego(rego);
  }

  public int updatePersonForVehicle(Vehicle vehicle) {
    log.info("Inside updatePersonForVehicle method of VehicleService");
    return vehicleRepository.updatePersonForVehicle(vehicle.getPersonid(), vehicle.getRego());
  }

  public List<Vehicle> findVehicles() {
    log.info("Inside findVehicles method of VehicleService");
    return vehicleRepository.findAll();
  }
}
