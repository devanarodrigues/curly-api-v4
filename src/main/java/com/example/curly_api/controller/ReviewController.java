package com.example.curly_api.controller;

import com.example.curly_api.dto.CreateReviewDto;
import com.example.curly_api.dto.UpdateReviewDto;
import com.example.curly_api.entity.Review;
import com.example.curly_api.repository.ReviewRepository;
import com.example.curly_api.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewController {

    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    @PostMapping("/review/{userId}")
    public ResponseEntity<Review> createReview(@PathVariable("userId") String userId,
                                               @RequestBody CreateReviewDto createReviewDto ){
        return ResponseEntity.ok(reviewService.createReview(userId, createReviewDto));
    }

    @GetMapping("/review/{userId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable("userId") String userId){

        return ResponseEntity.ok(reviewRepository.findByUserId(userId));
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId") String reviewId,
                                               @RequestBody UpdateReviewDto updateReviewDto){
        return ResponseEntity.ok(reviewService.updateReview(reviewId, updateReviewDto));
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable("reviewId") String reviewId){

        return ResponseEntity.ok().body("Review deletada com sucesso!");
    }

}
