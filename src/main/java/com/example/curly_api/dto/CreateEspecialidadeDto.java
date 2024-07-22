package com.example.curly_api.dto;

import com.example.curly_api.entity.User;

public record CreateEspecialidadeDto(Long idEspecialidade, String especialidadeUsuario, User user) {
}
