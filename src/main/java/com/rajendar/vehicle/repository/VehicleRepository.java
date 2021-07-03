package com.rajendar.vehicle.repository;

import com.rajendar.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

  Vehicle findByRego(String rego);

  @Modifying
  @Transactional
  @Query(value = "update Vehicle v set v.personid = ? where v.rego = ?",
      nativeQuery = true)
  int updatePersonForVehicle(Long personId, String rego);

}
