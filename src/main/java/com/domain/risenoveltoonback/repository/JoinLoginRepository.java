package com.domain.risenoveltoonback.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.risenoveltoonback.entity.UserEntity;

public interface JoinLoginRepository extends JpaRepository<UserEntity, Long>  {
    
    // 아이디로 회원 정보 조회    
    Optional<UserEntity> findByUserId(String userId);
}
