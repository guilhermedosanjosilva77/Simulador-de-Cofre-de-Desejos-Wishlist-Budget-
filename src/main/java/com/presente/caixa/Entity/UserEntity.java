package com.presente.caixa.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

    //ID
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_user;

    //RELACIONAMENTO TABELA ITEM
    @OneToMany (mappedBy = "userEntity")
    private List<ItemEntity> itemEntity;    

    //NOME
    private String nome;

    //EMAIL
    private String email;

    //SENHA
    private String senha;

    public UserEntity() {
    }

    public UserEntity(Long id_user, String nome, String email, String senha) {
        this.id_user = id_user;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    
    
}
