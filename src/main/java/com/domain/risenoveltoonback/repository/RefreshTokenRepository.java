package com.domain.risenoveltoonback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.risenoveltoonback.entity.RefreshTokenEntity;

import java.util.Optional; 

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {    
    Optional<RefreshTokenEntity> findByUserId(String userId);
}