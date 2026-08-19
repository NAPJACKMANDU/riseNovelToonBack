package com.domain.risenoveltoonback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.service.ToonNovelDataService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToonNovelDataController {
    
    private final ToonNovelDataService toonNovelDataService;

    @GetMapping("/mainToonNovel")
    public List<ToonNovelDto> mainToonNovel() {
        return toonNovelDataService.mainToonNovel();
    }
}
