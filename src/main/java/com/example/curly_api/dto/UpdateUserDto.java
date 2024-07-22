package com.example.curly_api.dto;

import com.example.curly_api.entity.Especialidade;

import java.util.List;

public record UpdateUserDto(String primeiroNome, String segundoNome, String nomeLoja, String senha, String cep, String logradouro, String cidade, String estado, String bairro, String foto, String descricao, String celular, int curvatura, int numero, List<Especialidade> especialidades) {

}
