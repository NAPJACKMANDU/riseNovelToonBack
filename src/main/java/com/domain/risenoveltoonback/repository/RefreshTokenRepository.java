package com.domain.risenoveltoonback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.risenoveltoonback.entity.RefreshToken;

import java.util.Optional; 

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {    
    Optional<RefreshToken> findByUserId(String userId);
}