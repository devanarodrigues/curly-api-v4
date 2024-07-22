package com.example.curly_api.controller;

import com.example.curly_api.dto.CreateEspecialidadeDto;
import com.example.curly_api.dto.UpdateEspecialidadeDto;
import com.example.curly_api.entity.Especialidade;
import com.example.curly_api.entity.User;
import com.example.curly_api.repository.EspecialidadeRepository;
import com.example.curly_api.repository.UserRepository;
import com.example.curly_api.service.EspecialidadeService;
import com.example.curly_api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/especialidade")
@AllArgsConstructor
public class EspecialidadeController {

    private EspecialidadeService especialidadeService;
    private EspecialidadeRepository especialidadeRepository;
    private UserRepository userRepository;
    private UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<Especialidade> createEspecialidade(@PathVariable("userId") String userId,
                                                             @RequestBody CreateEspecialidadeDto createEspecialidadeDto) {
        var exist = userRepository.findById(userId);

        if (exist.isPresent()) {
            return ResponseEntity.ok(especialidadeService.createEspecialidade(userId, createEspecialidadeDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idEspecialidade}")
    public ResponseEntity<Optional<Especialidade>> updateEspecialidade(@PathVariable("idEspecialidade") Long idEspecialidade,
                                                                       @RequestBody UpdateEspecialidadeDto updateEspecialidadeDto) {
        if (especialidadeRepository.existsById(idEspecialidade)) {
            return ResponseEntity.ok(especialidadeService.updateEspecialidade(idEspecialidade, updateEspecialidadeDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Especialidade>> getAllEspecialidades(@PathVariable("userId") String userId) {
        if (userRepository.existsById(userId)) {
            return ResponseEntity.ok(especialidadeService.getAllEspecialidades(userId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/{idEspecialidade}")
    public ResponseEntity deleteEspecialidade(@PathVariable("idEspecialidade") Long idEspecialidade,
                                                              @PathVariable("userId") String userId) {
        if (userRepository.existsById(userId)) {

            if (especialidadeRepository.existsById(idEspecialidade)) {
                especialidadeService.deleteEspecialidade(idEspecialidade, userId);
                return ResponseEntity.ok().body("Especialidade deletada com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
