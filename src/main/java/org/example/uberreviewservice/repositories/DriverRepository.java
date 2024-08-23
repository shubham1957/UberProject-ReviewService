package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

   Optional <Driver> findByIdAndLicenseNumber(Long id, String license_number);

   List<Driver> findAllByIdIn(List<Long> driverIds);

}
