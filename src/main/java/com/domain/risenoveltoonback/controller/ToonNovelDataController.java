package com.domain.risenoveltoonback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.joinLogin.MyPageDataDto;
import com.domain.risenoveltoonback.model.toonNovel.SetLoveToonNovelDto;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelEpisodeDto;
import com.domain.risenoveltoonback.service.ToonNovelDataService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToonNovelDataController {
    
    private final ToonNovelDataService toonNovelDataService;

    @GetMapping("/mainToonNovel")
    public List<ToonNovelDto> mainToonNovel() {
        return toonNovelDataService.mainToonNovel();
    }

    @GetMapping("/viewCount/{contentId}")
    public Optional<ToonNovelDto> viewCount(@PathVariable("contentId") Long contentId, Authentication authentication) {

        ToonNovelDto toonNovelDto = new ToonNovelDto();
        
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();
        toonNovelDto.setContentId(contentId);
        toonNovelDto.setUserId(userId);

        return toonNovelDataService.viewCount(toonNovelDto);
    }

    @PostMapping("/setLoveState")
    public void setLoveState(@RequestBody SetLoveToonNovelDto setLovesItem,  Authentication authentication) {
        
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();
        toonNovelDataService.setLoveState(userId, setLovesItem);
    }

    @GetMapping("/novelToonEpisodesData")
    public ResponseEntity<ApiResponse<List<ToonNovelEpisodeDto>>> novelToonEpisodesData(@RequestParam Long contentId) {
        return toonNovelDataService.novelToonEpisodesData(contentId);
    }
    
    
}
