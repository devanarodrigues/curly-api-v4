package com.example.curly_api.service;

import com.example.curly_api.repository.PostagemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class PostagemService {

    private PostagemRepository postagemRepository;

    public ResponseEntity deletePostagem(String postagemId){
        var exist = postagemRepository.findById(postagemId);

        if(exist.isPresent()){
            postagemRepository.deleteById(postagemId);
            return ResponseEntity.ok(exist.get());
        }else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }


}
