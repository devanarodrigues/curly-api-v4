package com.example.curly_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_especialidade")
@AllArgsConstructor
@NoArgsConstructor
public class Especialidade {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidade;

    @Getter
    @Setter
    private String especialidadeUsuario;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
