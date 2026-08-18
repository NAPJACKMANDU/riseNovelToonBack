package com.domain.risenoveltoonback.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.domain.risenoveltoonback.entity.ContentsEntity;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.repository.ToonNovelDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToonNovelDataService {
    
    private final ToonNovelDataRepository toonNovelDataRepository;

    public List<ToonNovelDto> mainToonNovel() {
            List<ContentsEntity> entities = toonNovelDataRepository.findAll();

    return entities.stream()
            .map(entity -> ToonNovelDto.builder()
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .author(entity.getAuthor())
                    .type(entity.getType())
                    .toonUrl(entity.getToonUrl())
                    .createdAt(entity.getCreatedAt())
                    .cpName(entity.getCpName())
                    .rightMember(entity.getRightMember())
                    .views(entity.getViews())
                    .leftMember(entity.getLeftMember())
                    .build())
            .collect(Collectors.toList());
    }
}
