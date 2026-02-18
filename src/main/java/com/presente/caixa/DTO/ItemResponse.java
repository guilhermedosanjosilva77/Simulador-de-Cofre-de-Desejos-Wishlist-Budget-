package com.presente.caixa.DTO;

import com.presente.caixa.Entity.ItemEntity;

//SEGURANÇA PARA SAIDA
public record ItemResponse(Long id_item, Double valorItem, Double valorDisponivel,Long idUser) {

    public ItemResponse(ItemEntity itemEntity) {
        this(
             itemEntity.getId_item(),
            itemEntity.getValorItem(),
            itemEntity.getValorDisponivel(),
        itemEntity.getUserEntity().getId()        );
    }
    
} 