package org.example.uberreviewservice.controllers;

import org.example.uberreviewservice.adapters.CreateReviewDtoToReviewAdapter;
import org.example.uberreviewservice.dtos.CreateReviewDto;
import org.example.uberreviewservice.dtos.ReviewDto;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter){
        this.reviewService=reviewService;
        this.createReviewDtoToReviewAdapter=createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request){
        Review incomingReview = this.createReviewDtoToReviewAdapter.convertDto(request);
        if(incomingReview==null){
            return new ResponseEntity<>("Invalid Arguments",HttpStatus.BAD_REQUEST);
        }
        Review review = this.reviewService.publishReview(incomingReview);
        ReviewDto response = ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .booking(review.getBooking().getId())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews(){
        List<Review> reviews = this.reviewService.findAllReviews();
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId){
        try {
            Optional<Review> review = this.reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId){
        try {
            boolean isDeleted = this.reviewService.deleteReviewById(reviewId);
            if (!isDeleted){
                return new ResponseEntity<>("Uanble to delete the Review",HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request){
        try {
            Review review = this.reviewService.updateReview(reviewId,request);
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
