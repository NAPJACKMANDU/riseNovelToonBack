package com.domain.risenoveltoonback.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_ID(400, "AUTH_001", "중복된 아이디입니다."),
    INVALID_PASSWORD(400, "AUTH_002", "비밀번호 형식이 올바르지 않습니다."),
    DUPLICATE_NICKNAME(409, "AUTH_001", "중복된 닉네임입니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
