package com.presente.caixa.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presente.caixa.DTO.UserRequest;
import com.presente.caixa.DTO.UserResponse;
import com.presente.caixa.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public UserResponse criar(@RequestBody UserRequest userRequest) {
        //TODO: process POST request
        
        return userService.criar(userRequest, null);
    }

    //READ- GET

    @GetMapping
    public List<UserResponse>buscar() {
        return userService.buscar();
    }

    //READ BY ID - GET

    @GetMapping("/{id_user}")
    public UserResponse buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }

    //UPDATE - PUT
    @PutMapping("/{id_user}")
    public UserResponse atualizar(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        
        return userService.atualizar(id, userRequest);
    }

    //DELETE - DELETE
    @DeleteMapping("/{id_user}")
    public void deletar(@PathVariable Long id){
        userService.deletar(id);
    }
    
    
    
    
}
