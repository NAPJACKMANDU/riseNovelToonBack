package com.domain.risenoveltoonback.exception;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}