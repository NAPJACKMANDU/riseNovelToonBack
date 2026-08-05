package com.domain.risenoveltoonback.repository.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;

@Mapper
public interface JoinLoginMapper {

    void joinUser(JoinFormDto signUpForm) ;
    int duplicateCheck(DuplicateCheckDto duplicateCheckDto);
}
