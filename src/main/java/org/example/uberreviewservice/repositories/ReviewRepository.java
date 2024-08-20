package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Now ReviewRepository class has extended all the methods and function provided by JpaRepository
    // By creating the object of the ReviewRepository we can access methods like findAll(), deleteById(), etc in our service layer

    Integer countAllByRatingIsLessThanEqual (Integer givenRating);

    List<Review> findAllByRatingIsLessThanEqual( Integer givenRating);

    List<Review> findAllByCreatedAtBefore (Date date);

    @Query("select r from Booking b inner join Review r where b.id= :bookingId")
    Review findReviewByBookingID (Long bookingId);
}
