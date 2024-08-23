package org.example.uberreviewservice.adapters;

import org.example.uberreviewservice.dtos.CreateReviewDto;
import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter{

    private BookingRepository bookingRepository;

    public  CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {
        Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());
        return booking.map(value -> Review.builder()
                .rating(createReviewDto.getRating())
                .booking(value)
                .content(createReviewDto.getContent())
                .build()).orElse(null);
    }
}
