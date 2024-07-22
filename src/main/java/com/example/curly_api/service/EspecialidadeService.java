package com.example.curly_api.service;

import com.example.curly_api.dto.CreateEspecialidadeDto;
import com.example.curly_api.dto.UpdateEspecialidadeDto;
import com.example.curly_api.entity.Especialidade;
import com.example.curly_api.repository.EspecialidadeRepository;
import com.example.curly_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EspecialidadeService {

    private EspecialidadeRepository especialidadeRepository;
    private UserRepository userRepository;

    public Especialidade createEspecialidade(String userId, CreateEspecialidadeDto createEspecialidadeDto) {
        var user = userRepository.getById(userId);
        var entity = new Especialidade(null, createEspecialidadeDto.especialidadeUsuario(), user);

        return especialidadeRepository.save(entity);
    }

    public Optional<Especialidade> updateEspecialidade(Long idEspecialidade, UpdateEspecialidadeDto updateEspecialidadeDto) {
        var entity = especialidadeRepository.findById(idEspecialidade);

        if (entity.isPresent()) {
            var especialidade = entity.get();

            if (updateEspecialidadeDto.especialidadeUsuario() != null) {
                especialidade.setEspecialidadeUsuario(updateEspecialidadeDto.especialidadeUsuario());
                especialidadeRepository.save(especialidade);
                return especialidadeRepository.findById(idEspecialidade);

            } else {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400));
            }
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public List<Especialidade> getAllEspecialidades(String userId) {
        var user = userRepository.findById(userId);

        if (user.isPresent()) {
            return especialidadeRepository.findByUserId(userId);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public ResponseEntity deleteEspecialidade(Long idEspecialidade, String userId) {

        if (userRepository.existsById(userId)) {
            if (especialidadeRepository.existsById(idEspecialidade)) {
                especialidadeRepository.deleteById(idEspecialidade);
                return ResponseEntity.ok().build();
            } else {
                throw new ResponseStatusException(HttpStatusCode.valueOf(404));
            }
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }
}
