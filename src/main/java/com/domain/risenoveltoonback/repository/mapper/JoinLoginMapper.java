package com.domain.risenoveltoonback.repository.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.domain.risenoveltoonback.model.joinLogin.DuplicateCheckDto;
import com.domain.risenoveltoonback.model.joinLogin.InformationChangeDto;
import com.domain.risenoveltoonback.model.joinLogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinLogin.MyPageDataDto;
import com.domain.risenoveltoonback.model.joinLogin.UserInfoDto;

@Mapper
public interface JoinLoginMapper {

    void joinUser(JoinFormDto signUpForm);
    int joinduplicateCheck(JoinFormDto signUpForm);
    int duplicateCheck(DuplicateCheckDto duplicateCheckDto);
    int loginUser(String userId);
    UserInfoDto userInfo(String userId);
    int informationChange(InformationChangeDto informationChangeDto);
    MyPageDataDto myPageData(String userId);
}
