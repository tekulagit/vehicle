package com.rajendar.vehicle.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.rajendar.vehicle.entity.Vehicle;
import com.rajendar.vehicle.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private VehicleService vehicleService;

  Vehicle vehicle;

  @BeforeEach
  void setUp() {
    vehicle = new Vehicle("NS1234","BMW","5 Series",2016,4,1998,123L);
  }

  @Test
  void addVehicle() {
    when(vehicleRepository.save(Mockito.any())).thenReturn(vehicle);
    assertThat(vehicleService.addVehicle(vehicle).getMake()).isEqualTo("BMW");
  }

  @Test
  void findByVehicleRego() {
    when(vehicleRepository.findByRego(Mockito.any())).thenReturn(vehicle);
    assertThat(vehicleService.findByVehicleRego("NS1234").getPersonid()).isEqualTo(123L);
  }

  @Test
  void updatePersonForVehicle() {
    when(vehicleRepository.updatePersonForVehicle(Mockito.any(), Mockito.any())).thenReturn(1);
    assertThat(vehicleService.updatePersonForVehicle(vehicle)).isEqualTo(1);
  }

  @Test
  void findVehicles() {
  }
}