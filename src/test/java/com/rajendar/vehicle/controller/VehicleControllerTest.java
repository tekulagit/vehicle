package com.rajendar.vehicle.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.rajendar.vehicle.delegate.VehicleDelegate;
import com.rajendar.vehicle.entity.Vehicle;
import com.rajendar.vehicle.service.VehicleService;
import com.rajendar.vehicle.util.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

  @Mock
  private VehicleService vehicleService;

  @Mock
  private VehicleDelegate vehicleDelegate;

  @InjectMocks
  private VehicleController vehicleController;

  Vehicle vehicle;

  @BeforeEach
  void setUp() {
    vehicle = new Vehicle("NS1234","BMW","5 Series",2016,4,1998,123L);
  }

  @Test
  void addVehicle() {
    when(vehicleService.addVehicle(Mockito.any())).thenReturn(vehicle);
    when(vehicleDelegate.checkPerson(Mockito.any())).thenReturn(true);
    assertThat(vehicleController.addVehicle(vehicle).getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  void addVehiclePersonNotExist() {
    when(vehicleDelegate.checkPerson(Mockito.any())).thenReturn(false);
    assertThat(vehicleController.addVehicle(vehicle).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void updateVehicle() {
    when(vehicleDelegate.checkPerson(Mockito.any())).thenReturn(true);
    when(vehicleDelegate.validateVehicleRegistration(Mockito.any())).thenReturn(false);
    when(vehicleService.findByVehicleRego(Mockito.any())).thenReturn(vehicle);
    when(vehicleService.updatePersonForVehicle(Mockito.any())).thenReturn(1);
    assertThat(vehicleController.updateVehicle("NS1234", vehicle).getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void updateVehicleBadRequestPersonNotPresent() {
    when(vehicleDelegate.checkPerson(Mockito.any())).thenReturn(false);
    when(vehicleService.findByVehicleRego(Mockito.any())).thenReturn(vehicle);
    assertThat(vehicleController.updateVehicle("NS1234", vehicle).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void updateVehicleBadRequestVehicleAlreadyRegistered() {
    when(vehicleDelegate.checkPerson(Mockito.any())).thenReturn(true);
    when(vehicleService.findByVehicleRego(Mockito.any())).thenReturn(vehicle);
    when(vehicleDelegate.validateVehicleRegistration(Mockito.any())).thenReturn(true);
    assertThat(vehicleController.updateVehicle("NS1234", vehicle).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void findVehicles() {
    when(vehicleService.findVehicles()).thenReturn(TestConstants.createPersonList());
    assertThat(vehicleController.findVehicles().getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(vehicleController.findVehicles().getBody().size()).isEqualTo(3);
  }

  @Test
  void findByVehicleRego() {
    when(vehicleService.findByVehicleRego(Mockito.any())).thenReturn(vehicle);
    assertThat(vehicleController.findByVehicleRego("NS1234").getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(vehicleController.findByVehicleRego("NS1234").getBody().getPersonid()).isEqualTo(123L);
  }
}