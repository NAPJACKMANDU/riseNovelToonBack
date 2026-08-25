package com.domain.risenoveltoonback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.service.ToonNovelDataService;

import lombok.RequiredArgsConstructor;

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
}
