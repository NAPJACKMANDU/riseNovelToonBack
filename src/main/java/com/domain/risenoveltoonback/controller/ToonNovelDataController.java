package com.domain.risenoveltoonback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.common.constants.ErrorCode;
import com.domain.risenoveltoonback.exception.CustomException;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.toonNovel.SetLoveToonNovelDto;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelCommentDto;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelEpisodeDto;
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

    // 찜 저장 혹은 지우기
    @PostMapping("/setLoveState")
    public void setLoveState(@RequestBody SetLoveToonNovelDto setLovesItem,  Authentication authentication) {
        
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();
        toonNovelDataService.setLoveState(userId, setLovesItem);
    }


    // 웹툰 소설별 각 에피소드
    @GetMapping("/novelToonEpisodesData")
    public ResponseEntity<ApiResponse<List<ToonNovelEpisodeDto>>> novelToonEpisodesData(@RequestParam int contentId, Authentication authentication) {
        
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();
        
        return toonNovelDataService.novelToonEpisodesData(contentId, userId);
    }

    // 찜 데이터 목록
    @PostMapping("/loveContents")
    public ResponseEntity<ApiResponse<List<ToonNovelDto>>> loveContents(Authentication authentication) {
        
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();

        return toonNovelDataService.loveContents(userId);
    }

    @PostMapping("/fetchEpisodes")
    public boolean fetchEpisodes(@RequestParam int contentId, Authentication authentication) {
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        String userId = authentication.getName();

        return toonNovelDataService.fetchEpisodes(contentId, userId);
    }

    // 댓글 등록
    @PostMapping("/commentHandler")
    public void commentHandler(@RequestBody ToonNovelCommentDto commentApiItem, Authentication authentication) {
        
        if (authentication == null) {
            throw new CustomException(ErrorCode.INFO_ERROR);
        }

        ToonNovelCommentDto toonNovelCommentDto = new ToonNovelCommentDto();

        toonNovelCommentDto.setUserId(authentication.getName());
        toonNovelCommentDto.setCommentText(commentApiItem.getCommentText());
        toonNovelCommentDto.setContentId(commentApiItem.getContentId());
        
        if(commentApiItem.getParentId() != null) toonNovelCommentDto.setParentId(commentApiItem.getParentId());
        
        toonNovelDataService.commentHandler(toonNovelCommentDto);
    }

    // 댓글 리스트
    @GetMapping("/commentList/{contentId}")
    public List<ToonNovelCommentDto> commentList(@PathVariable("contentId") int contentId) {
        return toonNovelDataService.commentList(contentId);
    }

    // 댓글 삭제
    @PostMapping("/deleteComment")
    public void deleteComment(@RequestParam int commentId) {
        System.out.println(commentId);
        toonNovelDataService.deleteComment(commentId);
    }
    
}
