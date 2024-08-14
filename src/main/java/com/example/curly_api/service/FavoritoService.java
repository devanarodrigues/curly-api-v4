package com.example.curly_api.service;

import com.example.curly_api.dto.CreateFavoritoDto;
import com.example.curly_api.entity.Favorito;
import com.example.curly_api.repository.FavoritoRepository;
import com.example.curly_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FavoritoService {

    private FavoritoRepository favoritoRepository;
    private UserRepository userRepository;

    public Favorito createFav(CreateFavoritoDto createFavoritoDto, String userId){
        var exist = userRepository.findById(userId);

        if(exist.isPresent()){
            var entity = new Favorito(UUID.randomUUID().toString(), exist.get(), createFavoritoDto.profissionalId());

            return favoritoRepository.save(entity);
        }else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public ResponseEntity deleteFav(String favId){
        var exist = favoritoRepository.findById(favId);

        if(exist.isPresent()){
            favoritoRepository.deleteById(favId);
            return ResponseEntity.ok(exist.get());
        }else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }
}
