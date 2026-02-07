package com.presente.caixa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presente.caixa.Entity.ItemEntity;

public interface ItemRepository extends JpaRepository <ItemEntity,Long>  {

    
} 
