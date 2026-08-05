package com.domain.risenoveltoonback.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;
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

        // 아이디, 닉네임 중복 여부
        public ResponseEntity<String> duplicateCheck(DuplicateCheckDto param) {
            
            if(Objects.isNull(param.getCheckData()) || param.getCheckData().isEmpty()) 
                throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
            
            int checkNum = joinLoginMapper.duplicateCheck(param);
        
        if (checkNum == 0) {
            return ResponseEntity.ok("SUCCESS");
        } else {
            if ("id".equals(param.getTitle())) {
                throw new CustomException(ErrorCode.DUPLICATE_ID);
            } else {
                throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
            }
        }
    } 
}