package com.example.curly_api.dto;

import com.example.curly_api.entity.User;

public record CreateFavoritoDto(String favId, User user, String profissionalId) {
}
