package com.presente.caixa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presente.caixa.Entity.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity,Long>{


}
