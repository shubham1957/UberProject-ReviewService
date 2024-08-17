package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Driver;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.BookingRepository;
import org.example.uberreviewservice.repositories.DriverRepository;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {
    private final DriverRepository driverRepository;
    //CommandLineRunner interface is used to execute code after the Spring Boot application has started.
    // Implementing this interface allows you to run specific logic as soon as the application context is loaded,
    // which is useful for tasks like initializing data, running scripts, or performing startup checks.

    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;
    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository driverRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Welcome to the UberReview Service!");
//        Review r = Review.builder()
//                .content("Great Ride")
//                .rating(4.5)
//                .build();
//        Booking b = Booking.builder()
//                .review(r)
//                .endDate(new Date())
//                .build();
//
//        bookingRepository.save(b);
//
//        List<Review> reviews = reviewRepository.findAll();
//
//        for (Review review : reviews) {
//            System.out.println(review.getContent());
//        }

//        reviewRepository.deleteById(2L);
//        Optional <Booking> b = bookingRepository.findById(1L);
//        if(b.isPresent()){
//            System.out.println(b.get().getDriver().getName());
//        }

//        Optional<Driver> driver = driverRepository.findById(1L);
//        if(driver.isPresent()){
//            System.out.println(driver.get().getName());
//            List<Booking> b = driver.get().getBookings();
//            //List<Booking> bookings = bookingRepository.findAllByDriverId(2L);
//            for(Booking booking : b){
//                System.out.println(booking.getId());
//            }
//
//        }
//        Optional<Driver> d = driverRepository.rawFindByIdAndLicenseNumber(3L,"DL2020111");
//        System.out.println(d.get().getName() + "---"+ d.get().getLicenseNumber());
//
//        List<Booking> b = d.get().getBookings();;
//        for(Booking booking : b){
//            System.out.println(booking.getPassenger().getName()+"---"+booking.getBookingStatus());
//        }

        Optional<Driver> d1 = driverRepository.hqlFindByIdAndLicense(1L,"DL2024123");

        System.out.println(d1.get().getName());



    }
}
