package com.presente.caixa.Service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.presente.caixa.DTO.UserRequest;
import com.presente.caixa.DTO.UserResponse;
import com.presente.caixa.Entity.UserEntity;
import com.presente.caixa.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE
    public UserResponse criar(UserRequest dados,UserEntity userEntity){
        //não permitir a criação de mais de um email
        boolean jaExiste = userRepository.existsByEmail(dados.email());
        if(jaExiste == true){
            throw new RuntimeException("Endereço de email já existe");
        }

        //DTO REQUEST, IRÁ REECEBER DO USUÁRIO
        userEntity.setNome(dados.nome());
        userEntity.setEmail(dados.email());
        userEntity.setSenha(dados.senha());

        userRepository.save(userEntity);
        return new UserResponse(userEntity);
    }

    //READ
    public List<UserResponse> buscar(){
        return userRepository.findAll().stream().map(UserResponse::new).toList();
    }

    //READ BY ID
    public UserResponse buscarPorId(Long id){
        UserEntity usuario =userRepository.findById(id).orElse(null);
        if (usuario != null) {
            return new UserResponse(usuario);
            
        }
        else{
            return null;
        }
    }

    //UPDATE
    public UserResponse atualizar(Long id, UserRequest atualizar){
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        if (userEntity == null) {
            return null;
            
        }
        
        userEntity.setNome(atualizar.nome());
        userEntity.setEmail(atualizar.email());
        userEntity.setSenha(atualizar.senha());

         userRepository.save(userEntity);
         return new UserResponse(userEntity);
    }   


    //DELETE
    public void deletar(Long id){
        userRepository.deleteById(id);
    }
    //TUDO QUE RECEBE UM OBJETO DE ENTITY PRECISA PASSAR PELO DTO
}
