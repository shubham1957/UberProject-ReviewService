package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {
    //CommandLineRunner interface is used to execute code after the Spring Boot application has started.
    // Implementing this interface allows you to run specific logic as soon as the application context is loaded,
    // which is useful for tasks like initializing data, running scripts, or performing startup checks.

    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;
    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to the UberReview Service!");
        Review r = Review.builder()
                .content("Great Ride")
                .rating(4.5)
                .build();
        Booking b = Booking.builder()
                .review(r)
                .endDate(new Date())
                .build();

        bookingRepository.save(b);

        List<Review> reviews = reviewRepository.findAll();

        for (Review review : reviews) {
            System.out.println(review.getContent());
        }

        //reviewRepository.deleteById(2L);


    }
}
