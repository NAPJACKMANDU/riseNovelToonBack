package com.domain.risenoveltoonback.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.repository.mapper.JoinLoginMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinLoginMapper joinLoginMapper;

    // 회원가입
    public ResponseEntity<String> join(JoinFormDto signUpForm) {

        if(Objects.isNull(signUpForm.getId())) {
            throw new CustomException(ErrorCode.DUPLICATE_ID);
        }
        joinLoginMapper.joinUser(signUpForm);
        return ResponseEntity.ok("SUCCESS");
    }
}