package com.presente.caixa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_item; 

    //NOME
    private String nomeItem;

    //VALOR
    private double valorItem;

    //VALOR EM CAIXA
    private double valorDisponivel;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    public ItemEntity() {
    }

    public ItemEntity(Long id_item, String nomeItem, double valorItem, double valorDisponivel, UserEntity userEntity) {
        this.id_item = id_item;
        this.nomeItem = nomeItem;
        this.valorItem = valorItem;
        this.valorDisponivel = valorDisponivel;
        this.userEntity = userEntity;
    }

    public Long getId_item() {
        return id_item;
    }

    public void setId_item(Long id_item) {
        this.id_item = id_item;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public double getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(double valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    


}
