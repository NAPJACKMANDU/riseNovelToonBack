package com.domain.risenoveltoonback.common.constants;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 1. 모든 상수의 인자 개수를 3개로 통일 (HttpStatus, String code, String message)
    INSERT_ID(HttpStatus.BAD_REQUEST, "INSERT_ID", "아이디를 입력해 주세요."),
    DUPLICATE_ID(HttpStatus.BAD_REQUEST, "DUPLICATE_ID", "이미 사용 중인 아이디입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "DUPLICATE_NICKNAME", "이미 사용 중인 닉네임입니다."),
    DUPLICATE_CHECK(HttpStatus.BAD_REQUEST, "DUPLICATE_CHECK", "중복 확인이 필요합니다."),
    AGAIN_CHECK(HttpStatus.BAD_REQUEST, "AGAIN_CHECK", "아이디 또는 비밀번호가 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR", "서버 내부 오류가 발생했습니다.");


    // 2. status 필드 타입을 int -> HttpStatus로 변경
    private final HttpStatus status;
    private final String code;
    private final String message;

}