package com.domain.risenoveltoonback.model.joinLogin;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageDataDto {
    private String userId; // 아이디
    private String nickname; // 닉네임
    private String cpName; // cp네임
    private int currentBalance; // 코인
    private Long contentId; // 웹툰 고유아이디
    private String title; // 타이틀
    private String author; // 작가
    private String type; // 웹툰 or 소설
    private String toonUrl; // 웹툰 표지
}
