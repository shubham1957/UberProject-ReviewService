package org.example.uberreviewservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    public ReviewRepository reviewRepository;

    public ReviewServiceImpl (ReviewRepository repository){
        this.reviewRepository=repository;
    }

    @Override
    public Optional<Review> findReviewById(Long id)  throws EntityNotFoundException {
        Optional<Review> review;
        try {
            review = this.reviewRepository.findById(id);
            if (review.isEmpty()){
                throw new EntityNotFoundException("Review with id "+ id + " not found");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            if (e.getClass()==EntityNotFoundException.class){
                throw new EntityNotFoundException("Review with id "+ id + " not found");
            }
            throw new FetchNotFoundException("Unable to fetch, Please try again later!",id);
        }
        return  review;
    }

    @Override
    public List<Review> findAllReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            this.reviewRepository.delete(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Review publishReview(Review review) {
        return  this.reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review newReviewData) {
        Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(newReviewData.getRating() != null){
            review.setRating(newReviewData.getRating());
        }

        if(newReviewData.getContent() != null){
            review.setContent(newReviewData.getContent());
        }
        return this.reviewRepository.save(review);
    }
}
