package com.rajendar.vehicle.delegate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import com.rajendar.vehicle.entity.Vehicle;
import com.rajendar.vehicle.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VehicleDelegateTest {

  @Mock
  private VehicleService vehicleService;

  @InjectMocks
  private VehicleDelegate vehicleDelegate;

  Vehicle vehicle;

  @BeforeEach
  void setUp() {
    vehicle = new Vehicle("NS1234","BMW","5 Series",2016,4,1998,123L);
  }

  @Test
  void validateVehicleRegistration() {
    when(vehicleService.findByVehicleRego(Mockito.any())).thenReturn(vehicle);
    assertThat(vehicleDelegate.validateVehicleRegistration("NS1234")).isEqualTo(true);
  }

}