package com.domain.risenoveltoonback.repository;

import org.springframework.data.repository.CrudRepository;

import com.domain.risenoveltoonback.entity.UserEntity;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;

public interface JoinRepository extends CrudRepository<UserEntity, Long>  {
    

}
