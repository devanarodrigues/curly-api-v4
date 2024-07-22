package com.example.curly_api.dto;

import com.example.curly_api.entity.Especialidade;
import com.example.curly_api.entity.Role;

import java.util.List;
import java.util.Set;

public record CreateUserDto(String primeiroNome, String segundoNome, String nomeLoja, String email, String senha, String cep, String logradouro, String cidade, String estado, String bairro, String foto, String cnpj, String descricao, String celular, int curvatura, int numero, List<Especialidade> especialidades, List<Role> roles) {


}
