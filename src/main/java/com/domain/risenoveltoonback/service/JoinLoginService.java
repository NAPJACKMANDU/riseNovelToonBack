package com.domain.risenoveltoonback.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.common.constants.SuccessCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.LoginFormDto;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;
import com.domain.risenoveltoonback.repository.mapper.JoinLoginMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinLoginService {

    private final JoinLoginMapper joinLoginMapper;

    // 회원가입 
    // TODO : 본인 인증, 카카오로 회원가입, 애플로 회원가입, security
    public ResponseEntity<ApiResponse<Void>> join(JoinFormDto signUpForm) {

        int joinIdNickName = joinLoginMapper.joinduplicateCheck(signUpForm);

        if(joinIdNickName != 0) {
            throw new CustomException(ErrorCode.DUPLICATE_CHECK);
        }

        joinLoginMapper.joinUser(signUpForm);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_JOIN_UP));
    }

        // 아이디, 닉네임 중복 여부
        public ResponseEntity<ApiResponse<Void>> duplicateCheck(DuplicateCheckDto param) {
            
            int checkNum = joinLoginMapper.duplicateCheck(param);
        
        if (checkNum == 0) {
            if ("id".equals(param.getTitle())) {
                return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_CHECK_ID));
            } else {
                return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_CHECK_NICKNAME));
            }
        } else {
            if ("id".equals(param.getTitle())) {
                throw new CustomException(ErrorCode.DUPLICATE_ID);
            } else {
                throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
            }
        }
    } 
        // 로그인
        public ResponseEntity<ApiResponse<Void>> login(LoginFormDto loginForm) {
                int result = joinLoginMapper.loginUser(loginForm);
                if(result > 0) {
                    return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_JOIN_UP));
                } else {
                    throw new CustomException(ErrorCode.AGAIN_CHECK);
                }
             
        }
}