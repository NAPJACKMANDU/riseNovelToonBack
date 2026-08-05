package com.domain.risenoveltoonback.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import com.domain.risenoveltoonback.common.constants.ErrorCode;

import java.net.URI;
import java.time.Instant;

public class CustomException extends ErrorResponseException {

    public CustomException(ErrorCode errorCode) {
        // HttpStatusCode와 ProblemDetail을 다루는 ErrorResponseException 생성자 호출
        super(errorCode.getStatus(), createProblemDetail(errorCode), null);
    }

    private static ProblemDetail createProblemDetail(ErrorCode errorCode) {
        // forStatusAndDetail(HttpStatusCode status, String detail) 사용
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                errorCode.getStatus(),
                errorCode.getMessage()
        );
        
        // RFC 9457 부가 필드 세팅
        problemDetail.setTitle(errorCode.name());
        problemDetail.setType(URI.create("/errors/" + errorCode.getCode().toLowerCase()));
        problemDetail.setProperty("code", errorCode.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}