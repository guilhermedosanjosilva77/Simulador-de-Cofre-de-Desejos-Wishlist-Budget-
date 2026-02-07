package com.presente.caixa.DTO;

//SEGURANÇA PARA A ENTRADA
public record ItemRequest(String nomeItem,
    Double valorItem,
    Double valorDisponivel,
    Long idUsuario) {} 
