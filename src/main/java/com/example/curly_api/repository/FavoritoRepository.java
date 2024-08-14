package com.example.curly_api.repository;

import com.example.curly_api.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, String> {
}
