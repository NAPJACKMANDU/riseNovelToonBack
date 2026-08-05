package com.domain.risenoveltoonback.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.domain.risenoveltoonback.common.constants.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * CustomException 처리
     * CustomException이 ErrorResponseException을 상속하므로, 
     * getBody()를 호출하면 RFC 9457 규격의 ProblemDetail을 반환합니다.
     */
    @ExceptionHandler(CustomException.class)
    public ProblemDetail handleCustomException(CustomException e) {
        return e.getBody();
    }

    /**
     * 기타 처리되지 않은 예외 공통 처리
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                ErrorCode.INTERNAL_SERVER_ERROR.getStatus(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
        );
        problemDetail.setProperty("code", ErrorCode.INTERNAL_SERVER_ERROR.getCode());
        return problemDetail;
    }
}