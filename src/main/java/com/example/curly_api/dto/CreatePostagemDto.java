package com.example.curly_api.dto;

import com.example.curly_api.entity.User;

public record CreatePostagemDto(String postagemId, User user, String descricao, String imagem) {
}
