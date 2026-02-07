package com.presente.caixa.DTO;

public record ItemRequest(String nomeItem,
    String valorItem,
    String valorDisponivel,
    Long idUsuario) {} 
