package com.domain.risenoveltoonback.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 모든 상수의 인자 개수를 3개로 통일 (HttpStatus, String code, String message)
    SUCCESS_CHECK_ID("SUCCESS_CHECK_ID", "사용 가능한 아이디입니다."),
    SUCCESS_CHECK_NICKNAME("SUCCESS_CHECK_NICKNAME", "사용 가능한 닉네임입니다."),
    SUCCESS_JOIN_UP("SUCCESS_JOIN_UP", "회원가입이 완료되었습니다."),
    SUCCESS_LOGIN("SUCCESS_LOGIN", "로그인 성공!"),
    SUCCESS("S000", "성공");

    private final String code;
    private final String message;
   
};
