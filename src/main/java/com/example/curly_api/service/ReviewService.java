package com.example.curly_api.service;

import com.example.curly_api.dto.CreateReviewDto;
import com.example.curly_api.dto.UpdateReviewDto;
import com.example.curly_api.entity.Review;
import com.example.curly_api.repository.ReviewRepository;
import com.example.curly_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;

    public Review createReview(String userId, CreateReviewDto createReviewDto){
        var user = userRepository.findById(userId);

        if(user.isPresent()){
            var review = new Review(
                    UUID.randomUUID().toString(),
                    createReviewDto.descricao(),
                    user.get()
            );

            return reviewRepository.save(review);
        }else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void deleteReview(String reviewId) {
        var review = reviewRepository.findById(reviewId);
        if(review.isPresent()){
            reviewRepository.deleteById(reviewId);
        }else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
    }

    public Review updateReview(String reviewId, UpdateReviewDto updateReviewDto) {
        var entity = reviewRepository.findById(reviewId);

        if(entity.isPresent()){
            var review = entity.get();
            if(updateReviewDto.descricao() != null){
                review.setDescricao(updateReviewDto.descricao());
            }
                return reviewRepository.save(review);
        }else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }
}
