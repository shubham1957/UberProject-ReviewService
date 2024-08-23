package org.example.uberreviewservice.dtos;

import lombok.*;

@Getter
@Setter

// DTO to create a review
public class CreateReviewDto {

    private String content;

    private Double rating;

    private Long bookingId;
}
