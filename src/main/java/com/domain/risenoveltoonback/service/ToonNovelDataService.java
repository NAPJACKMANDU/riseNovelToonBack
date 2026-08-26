package com.domain.risenoveltoonback.service;

import com.domain.risenoveltoonback.repository.RefreshTokenRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.domain.risenoveltoonback.entity.ContentsEntity;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.repository.ToonNovelDataRepository;
import com.domain.risenoveltoonback.repository.mapper.ToonNovelDataMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToonNovelDataService {
    
        private final ToonNovelDataRepository toonNovelDataRepository;
        private final ToonNovelDataMapper toonNovelDataMapper;

    public List<ToonNovelDto> mainToonNovel() {
            List<ContentsEntity> entities = toonNovelDataRepository.findAll();

    return entities.stream()
            .map(entity -> ToonNovelDto.builder()
                    .contentId(entity.getContentId())
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

        public Optional<ToonNovelDto> viewCount(ToonNovelDto toonNovelDto) {
            String userId = toonNovelDto.getUserId();
            Long contentId = toonNovelDto.getContentId();

            // 1. 유저가 해당 콘텐츠를 조회했는지 확인
            boolean alreadyViewed = toonNovelDataMapper.existsContent(userId, contentId) > 0;

            if (!alreadyViewed) {
                // 3. 조회수 증가
                toonNovelDataMapper.updateView(userId, contentId);
                toonNovelDataMapper.upCountView(contentId);
            }

            // 4. 콘텐츠 반환
            return toonNovelDataMapper.findAllToonNovel(contentId);
        }
}
