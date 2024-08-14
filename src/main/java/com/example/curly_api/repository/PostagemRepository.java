package com.example.curly_api.repository;

import com.example.curly_api.entity.Favorito;
import com.example.curly_api.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, String> {
}
