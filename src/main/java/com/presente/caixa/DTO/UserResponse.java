package com.presente.caixa.DTO;

import com.presente.caixa.Entity.UserEntity;

//DTO RESPONSE PARA SAIDA
public record UserResponse(Long id_user,String nome,String email) {

    public UserResponse(UserEntity user){
        this(user.getId_user(),user.getNome(),user.getEmail());
    }
} 
