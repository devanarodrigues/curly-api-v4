package com.example.curly_api.controller;

import com.example.curly_api.dto.CreateFavoritoDto;
import com.example.curly_api.service.FavoritoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorito")
@AllArgsConstructor
public class FavoritoController {

    private FavoritoService favoritoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{idUser}")
    public ResponseEntity createFav(@PathVariable("idUser") String idUser,
                                    @RequestBody CreateFavoritoDto createFavoritoDto){

        return ResponseEntity.ok(favoritoService.createFav(createFavoritoDto,idUser));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{favId}")
    public ResponseEntity deleteFav(@PathVariable("favId") String favId){
        return ResponseEntity.ok(favoritoService.deleteFav(favId));
    }
}
