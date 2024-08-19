package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

   Optional <Driver> findByIdAndLicenseNumber(Long id, String license_number);

   // MYSQL Raw query -- YOU HAVE TO GIVE COLUMN NAME AS IT IS (license_number), THE ERROR (SYNTACTICAL ERROR) NOT DETECT AT COMPILE TIME
   // during run time when we call this function, it will execute the sql query and if col names are different, then the error will be thrown
   // so, if raw sql have an error it can't be detected at compile time so it become problem of testing the queries manually
   // to avoid this the HQL (HIBERNATE QUERY LANGUAGE CAME INTO EXISTENCE)
   @Query(nativeQuery = true, value = "SELECT * FROM DRIVER WHERE id=:id AND license_number=:license") // raw mysql query, error is thrown at runtime
   Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);

   @Query("SELECT d FROM Driver d WHERE d.id = :id AND d.licenseNumber = :ln") // Hibernate query, error is thrown at compile time, so testing become easy
   Optional<Driver> hqlFindByIdAndLicense(Long id, String ln);

   List<Driver> findAllByIdIn(List<Long> driverIds);

}
