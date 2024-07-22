package com.example.curly_api.controller;

import com.example.curly_api.dto.LoginRequestDto;
import com.example.curly_api.dto.LoginResponseDto;
import com.example.curly_api.entity.User;
import com.example.curly_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestController
@AllArgsConstructor
public class TokenController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        var user = userRepository.findByEmail(loginRequestDto.email());
        User userData = userRepository.findByEmail(loginRequestDto.email()).get();

        if (user.isEmpty() || user.get().isLoginCorrect(loginRequestDto, bCryptPasswordEncoder)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("curly-api-v2")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponseDto(jwtValue, expiresIn, userData));
    }
}


