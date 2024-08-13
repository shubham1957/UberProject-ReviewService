package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Now ReviewRepository class has extended all the methods and function provided by JpaRepository
    // By creating the object of the ReviewRepository we can access methods like findAll(), deleteById(), etc in our service layer
}
