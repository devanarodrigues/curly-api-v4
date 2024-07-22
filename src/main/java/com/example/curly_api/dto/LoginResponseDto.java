package com.example.curly_api.dto;

import com.example.curly_api.entity.User;

public record LoginResponseDto(String acessToken, Long expiresIn, User user) {
}
