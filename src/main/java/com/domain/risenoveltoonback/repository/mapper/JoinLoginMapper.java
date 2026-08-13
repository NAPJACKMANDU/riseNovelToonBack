package com.domain.risenoveltoonback.repository.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.UserInfoDto;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;

@Mapper
public interface JoinLoginMapper {

    void joinUser(JoinFormDto signUpForm) ;
    int joinduplicateCheck(JoinFormDto signUpForm);
    int duplicateCheck(DuplicateCheckDto duplicateCheckDto);
    int loginUser(String userId);
    UserInfoDto myPageData(String userId);
}
