package com.example.curly_api.dto;

import com.example.curly_api.entity.User;

import java.util.UUID;

public record CreateReviewDto(UUID idReview, String descricao, User user) {
}
