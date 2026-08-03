package com.domain.risenoveltoonback.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.repository.mapper.JoinLoginMapper;

@Service
public class JoinService {

    JoinLoginMapper joinLoginMapper;

    public ResponseEntity<String> join(JoinFormDto signUpForm) {
        // TODO: DB 저장 로직 (Repository 사용 등) 작성 위치
        System.out.println("Service에서 처리 중: " + signUpForm.getId());
        joinLoginMapper.joinUser(signUpForm);
        return ResponseEntity.ok("SUCCESS");
    }
}