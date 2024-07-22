package com.example.curly_api.repository;

import com.example.curly_api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository  extends JpaRepository<Review, String> {

    List<Review> findByUserId(String userId);
}
