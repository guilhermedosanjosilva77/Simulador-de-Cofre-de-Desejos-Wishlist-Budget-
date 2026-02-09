package com.presente.caixa.DTO;

import com.presente.caixa.Entity.UserEntity;

//DTO RESPONSE PARA SAIDA
public record UserResponse(Long id,String nome,String email) {

    public UserResponse(UserEntity user){
        this(user.getId(),user.getNome(),user.getEmail());
    }
} 
