package com.domain.risenoveltoonback.repository.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;

@Mapper
public interface JoinLoginMapper {

    void joinUser(JoinFormDto signUpForm) ;

}
