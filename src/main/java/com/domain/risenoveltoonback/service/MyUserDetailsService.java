package com.domain.risenoveltoonback.service;

import com.domain.risenoveltoonback.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.domain.risenoveltoonback.repository.JoinLoginRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final JoinLoginRepository joinLoginRepository;
    
    @Override    
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {    
    // 1. DB에서 username으로 회원 조회        
    UserEntity member = joinLoginRepository.findByUserId(userId)                
    .orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));         
    // 2. 시큐리티가 이해할 수 있는 UserDetails 객체로 변환해서 반환        
    return User.builder()                
    .username(member.getUserId())                
    .password(member.getPassword())              
    .build();    
    }
}
