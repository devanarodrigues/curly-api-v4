package com.example.curly_api.entity;

import com.example.curly_api.dto.LoginRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    @Getter
    @CreationTimestamp
    private Instant creationTimeStamp;

    @Getter
    @Setter
    @UpdateTimestamp
    private Instant updateTimeStamp;

    @Getter
    @Setter
    private String primeiroNome;
    @Getter
    @Setter
    private String segundoNome;

    @Getter
    @Setter
    @Column(unique = true)
    private String nomeLoja;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String senha;

    @Getter
    @Setter
    private String cep;

    @Getter
    @Setter
    private String logradouro;

    @Getter
    @Setter
    private String cidade;

    @Getter
    @Setter
    private String estado;

    @Getter
    @Setter
    private String bairro;

    @Getter
    @Setter
    private String foto;

    @Getter
    @Setter
    @Column(unique = true)
    private String cnpj;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @Column(unique = true)
    private String celular;

    @Getter
    @Setter
    private int curvatura;

    @Getter
    @Setter
    private int numero;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Especialidade> especialidades;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Review> reviews;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Favorito> favoritos;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Postagem> postagens;

    @Getter
    @Setter
    private boolean isAccountNonExpired;

    @Getter
    @Setter
    private boolean isAccountNonLocked;

    @Getter
    @Setter
    private boolean isCredentialsNonExpired;

    @Getter
    @Setter
    private boolean isEnabled;

    public User(String id, Instant creationTimeStamp, Instant updateTimeStamp, String primeiroNome, String segundoNome, String nomeLoja, String email, String senha, String cep, String logradouro, String cidade, String estado, String bairro, String foto, String cnpj, String descricao, String celular, int curvatura, int numero, List<Especialidade> especialidades, List<Role> roles) {
        this.id = id;
        this.creationTimeStamp = creationTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.nomeLoja = nomeLoja;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.foto = foto;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.celular = celular;
        this.curvatura = curvatura;
        this.numero = numero;
        this.especialidades = especialidades;
        this.roles = roles;
    }

    // security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean isLoginCorrect(LoginRequestDto loginRequestDto, PasswordEncoder passwordEncoder) {
        return !passwordEncoder.matches(loginRequestDto.senha(), this.senha);
    }
}

