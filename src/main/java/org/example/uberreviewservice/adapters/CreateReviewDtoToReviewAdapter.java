package org.example.uberreviewservice.adapters;

import org.example.uberprojectentityservice.models.Review;
import org.example.uberreviewservice.dtos.CreateReviewDto;

public interface CreateReviewDtoToReviewAdapter {

    public Review convertDto (CreateReviewDto createReviewDto);
}


