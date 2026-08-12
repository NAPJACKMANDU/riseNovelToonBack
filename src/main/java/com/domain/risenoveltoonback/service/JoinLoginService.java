package com.domain.risenoveltoonback.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.common.constants.SuccessCode;
import com.domain.risenoveltoonback.entity.RefreshTokenEntity;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.jwt.JwtToken;
import com.domain.risenoveltoonback.jwt.JwtTokenProvider;
import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.LoginFormDto;
import com.domain.risenoveltoonback.model.joinlogin.MyPageDataDto;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;
import com.domain.risenoveltoonback.repository.JoinLoginRepository;
import com.domain.risenoveltoonback.repository.RefreshTokenRepository;
import com.domain.risenoveltoonback.repository.mapper.JoinLoginMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class JoinLoginService {

    private final JoinLoginMapper joinLoginMapper;
    private final JoinLoginRepository joinLoginRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    
    // 회원가입
    // TODO : 본인 인증, 카카오로 회원가입, 애플로 회원가입
    public ResponseEntity<ApiResponse<Void>> join(JoinFormDto signUpForm) {

        // 아이디 중복 체크
        if(joinLoginRepository.findByUserId(signUpForm.getUserId()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_CHECK);
        }

        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());
        signUpForm.setPassword(encodedPassword);

        joinLoginMapper.joinUser(signUpForm);

        return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_JOIN_UP));
    }

        // 아이디, 닉네임 중복 여부
        public ResponseEntity<ApiResponse<Void>> duplicateCheck(DuplicateCheckDto param) {
            
        if (joinLoginMapper.duplicateCheck(param) == 0) {
            if ("userId".equals(param.getTitle())) {
                return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_CHECK_ID));
            } else {
                return ResponseEntity.ok(ApiResponse.success(SuccessCode.SUCCESS_CHECK_NICKNAME));
            }
        } else {
            if ("userId".equals(param.getTitle())) {
                throw new CustomException(ErrorCode.DUPLICATE_ID);
            } else {
                throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
            }
        }
    } 

        // 로그인
        public ResponseEntity<ApiResponse<MyPageDataDto>> login(LoginFormDto loginForm) {
        
            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성        
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginForm.getUserId(), loginForm.getPassword());
        
            Authentication authentication;
            try {
                // 2. 실제 검증 (사용자 비밀번호 체크)
                authentication = authenticationManager.authenticate(authenticationToken);
            } catch (BadCredentialsException e) {
                // 비밀번호 불일치 시 커스텀 예외 던지기
                throw new CustomException(ErrorCode.AGAIN_CHECK);
            }
        
            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            JwtToken jwtToken = jwtTokenProvider.createToken(authentication);
        
            RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                    .userId(authentication.getName())
                    .refreshToken(jwtToken.getRefreshToken())
                    .build();
        
            refreshTokenRepository.save(refreshToken);
        
            if (joinLoginMapper.loginUser(loginForm.getUserId()) > 0) {
                MyPageDataDto myPageDataDto = joinLoginMapper.myPageData(loginForm.getUserId());
                myPageDataDto.setAccessToken(jwtToken.getAccessToken());
                myPageDataDto.setRefreshToken(jwtToken.getRefreshToken());
                return ResponseEntity.ok(ApiResponse.success(myPageDataDto));
            } else {
                throw new CustomException(ErrorCode.AGAIN_CHECK);
            }
        }


        // 새로운 토큰 받기
        public ResponseEntity<ApiResponse<JwtToken>> reissue(String refreshToken) {
            // 1. Refresh Token 검증
            if (!jwtTokenProvider.validateToken(refreshToken)) {
               throw new CustomException(ErrorCode.AGAIN_CHECK);
            }
        
            // 2. 토큰에서 User ID 가져오기
            Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
        
            // 3. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져옴
            RefreshTokenEntity dbRefreshToken = refreshTokenRepository.findByUserId(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
        
            // 4. 토큰 일치 여부 검사 (핵심!)
            if (!dbRefreshToken.getRefreshToken().equals(refreshToken)) {
                throw new CustomException(ErrorCode.AGAIN_CHECK);
            }
        
            // 5. 새로운 토큰 생성
            JwtToken newJwtToken = jwtTokenProvider.createToken(authentication);
        
            // 6. 저장소 정보 업데이트 (Rotation)
            dbRefreshToken.updateRefreshToken(newJwtToken.getRefreshToken());
        
        return ResponseEntity.ok(ApiResponse.success(newJwtToken));
    }

    // public ResponseEntity<ApiResponse<MyPageDataDto>> myPageData(String userId){
    //     MyPageDataDto myPageDataDto = joinLoginMapper.myPageData(userId);
    //      return ResponseEntity.ok(ApiResponse.success(myPageDataDto));
    // }
}