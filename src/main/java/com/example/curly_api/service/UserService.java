package com.example.curly_api.service;

import com.example.curly_api.dto.CreateUserDto;
import com.example.curly_api.dto.UpdateUserDto;
import com.example.curly_api.entity.Role;
import com.example.curly_api.entity.User;
import com.example.curly_api.repository.EspecialidadeRepository;
import com.example.curly_api.repository.RoleRepository;
import com.example.curly_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private EspecialidadeRepository especialidadeRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto createUserDto) {
        var exists = userRepository.findByEmail(createUserDto.email());

        if (exists.isEmpty()) {
            var entity = new User(UUID.randomUUID().toString(),
                    Instant.now(),
                    null,
                    createUserDto.primeiroNome(),
                    createUserDto.segundoNome(),
                    createUserDto.nomeLoja(),
                    createUserDto.email(),
                    null,
                    createUserDto.cep(),
                    createUserDto.logradouro(),
                    createUserDto.cidade(),
                    createUserDto.estado(),
                    createUserDto.bairro(),
                    createUserDto.foto(),
                    createUserDto.cnpj(),
                    createUserDto.descricao(),
                    createUserDto.celular(),
                    createUserDto.curvatura(),
                    createUserDto.numero(),
                    createUserDto.especialidades(),
                    null
            );
            entity.setSenha(passwordEncoder.encode(createUserDto.senha()));

            if (entity.getNomeLoja() == null) {
                entity.setRoles(List.of(roleRepository.findByName(Role.Values.CLIENT.name())));
            } else {
                entity.setRoles(List.of(roleRepository.findByName(Role.Values.PROFESSIONAL.name())));
            }
            return userRepository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422));
        }

    }

    public Optional<User> getUserById(String id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return userRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(String id, UpdateUserDto updateUserDto) {
        var entity = userRepository.findById(id);

        if (entity.isPresent()) {
            var user = entity.get();

            if (updateUserDto.bairro() != null) {
                user.setBairro(updateUserDto.bairro());
            }
            if (updateUserDto.cep() != null) {
                user.setCep(updateUserDto.cep());
            }
            if (updateUserDto.cidade() != null) {
                user.setCidade(updateUserDto.cidade());
            }
            if (updateUserDto.estado() != null) {
                user.setEstado(updateUserDto.estado());
            }
            if (updateUserDto.logradouro() != null) {
                user.setLogradouro(updateUserDto.logradouro());
            }
            if (updateUserDto.numero() != 0) {
                user.setNumero(updateUserDto.numero());
            }
            if (updateUserDto.descricao() != null) {
                user.setDescricao(updateUserDto.descricao());
            }
            if (updateUserDto.nomeLoja() != null) {
                user.setNomeLoja(updateUserDto.nomeLoja());
            }
            if (updateUserDto.foto() != null) {
                user.setFoto(updateUserDto.foto());
            }
            if (updateUserDto.celular() != null) {
                user.setCelular(updateUserDto.celular());
            }
            if (updateUserDto.curvatura() != 0) {
                user.setCurvatura(updateUserDto.curvatura());
            }
            if (updateUserDto.primeiroNome() != null) {
                user.setPrimeiroNome(updateUserDto.primeiroNome());
            }
            if (updateUserDto.segundoNome() != null) {
                user.setSegundoNome(updateUserDto.segundoNome());
            }
            if (updateUserDto.senha() != null) {
                user.setSenha(updateUserDto.senha());
            }
            if (updateUserDto.especialidades() != null) {
                user.setEspecialidades(updateUserDto.especialidades());
            }
            userRepository.save(user);

            return userRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void deleteUser(String userId) {
        var user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

    }
}
