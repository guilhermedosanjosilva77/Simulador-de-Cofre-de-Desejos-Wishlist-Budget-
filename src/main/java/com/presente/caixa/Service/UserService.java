package com.presente.caixa.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.presente.caixa.Entity.UserEntity;
import com.presente.caixa.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE
    public UserEntity criar(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    //READ
    public List<UserEntity> buscar(){
        return userRepository.findAll();
    }

    //READ BY ID
    public UserEntity buscarPorId(Long id_user){
        return userRepository.findById(id_user).orElse(null);
    }

    //UPDATE
    public UserEntity atualizar(Long id_user, UserEntity atualizar){
        UserEntity userEntity = buscarPorId(id_user);

        if (userEntity == null) {
            return null;
            
        }
        
        userEntity.setNome(atualizar.getNome());
        userEntity.setEmail(atualizar.getEmail());
        userEntity.setSenha(atualizar.getSenha());

        return userRepository.save(userEntity);
    }    

    //DELETE
    public void deletar(Long id_user){
        userRepository.deleteById(id_user);
    }
}
