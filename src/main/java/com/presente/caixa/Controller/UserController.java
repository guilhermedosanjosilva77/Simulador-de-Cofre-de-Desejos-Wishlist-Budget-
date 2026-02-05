package com.presente.caixa.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presente.caixa.Entity.UserEntity;
import com.presente.caixa.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE - POST
    @PostMapping
    public UserEntity criar(@RequestBody UserEntity userEntity) {
        //TODO: process POST request
        
        return userService.criar(userEntity);
    }

    //READ- GET

    @GetMapping
    public List<UserEntity>buscar() {
        return userService.buscar();
    }

    //READ BY ID - GET

    @GetMapping("{/id_user}")
    public UserEntity buscarPorId(@PathVariable Long id_user) {
        return userService.buscarPorId(id_user);
    }

    //UPDATE - PUT
    @PutMapping("/{id_user}")
    public UserEntity atualizar(@PathVariable Long id_user, @RequestBody UserEntity userEntity) {
        
        return userService.atualizar(id_user, userEntity);
    }

    //DELETE - DELETE
    @DeleteMapping
    public void deletar(@PathVariable Long id_user){
        userService.deletar(id_user);
    }
    
    
    
    
}
