package com.presente.caixa.DTO;

//DTO DE SEGURANÇA PARA ENTRADA
public record UserRequest(String email, String nome, String senha) {
}
