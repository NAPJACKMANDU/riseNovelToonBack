package com.domain.risenoveltoonback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.jwt.JwtToken;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.joinLogin.DuplicateCheckDto;
import com.domain.risenoveltoonback.model.joinLogin.InformationChangeDto;
import com.domain.risenoveltoonback.model.joinLogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinLogin.LoginFormDto;
import com.domain.risenoveltoonback.model.joinLogin.MyPageDataDto;
import com.domain.risenoveltoonback.model.joinLogin.UserInfoDto;
import com.domain.risenoveltoonback.service.JoinLoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor 
public class JoinLoginController {

    private final JoinLoginService joinLoginService;

    @PostMapping("/join") // 회원가입
    public ResponseEntity<ApiResponse<Void>>  joinController(@RequestBody JoinFormDto signUpForm) {
       return joinLoginService.join(signUpForm);
    }

    @GetMapping("/duplicateCheck") // 아이디, 닉네임 중복 확인
    public ResponseEntity<ApiResponse<Void>> getDuplicateCheck(@ModelAttribute DuplicateCheckDto checkData) {
        return joinLoginService.duplicateCheck(checkData);
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<ApiResponse<UserInfoDto>> loginController(@RequestBody LoginFormDto loginForm) {
       return joinLoginService.login(loginForm);
    }

    @PostMapping("/reissue") // 재토큰
    public ResponseEntity<ApiResponse<JwtToken>> reissue(@RequestBody JwtToken jwtToken) {        
        return joinLoginService.reissue(jwtToken.getRefreshToken());    
    }

    @PostMapping("/myPage") // 마이페이지 진입 시
    public ResponseEntity<ApiResponse<MyPageDataDto>> getMyPageData(Authentication authentication) {
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }
        String userId = authentication.getName();
        return joinLoginService.myPageData(userId);
    }

    @PostMapping("/informationChange") // Nickname, cpName 정보 변경
    public ResponseEntity<ApiResponse<Void>> informationChange(@RequestBody InformationChangeDto informationChangeDto, Authentication authentication) {
         if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();
        informationChangeDto.setUserId(userId);
        
        return joinLoginService.informationChange(informationChangeDto);
    }
}
       