package com.domain.risenoveltoonback.model;

import lombok.Getter;
import java.time.Instant;

import com.domain.risenoveltoonback.common.constants.SuccessCode;

@Getter
public class ApiResponse<T> {
    private final String code;
    private final String message;
    private final T data;
    private final Instant timestamp;

    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }

    // 성공 응답 생성 메소드 (데이터가 있는 경우)
    public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
        return new ApiResponse<>(successCode.getCode(), successCode.getMessage(), data);
    }

    // 성공 응답 생성 메소드 (데이터가 없는 경우)
    public static <T> ApiResponse<T> success(SuccessCode successCode) {
        return new ApiResponse<>(successCode.getCode(), successCode.getMessage(), null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getMessage(), data);
    }
}