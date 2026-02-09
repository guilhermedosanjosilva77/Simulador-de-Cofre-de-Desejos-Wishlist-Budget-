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
    private Long id;

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

    public UserEntity(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
