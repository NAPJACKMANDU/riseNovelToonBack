package com.domain.risenoveltoonback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.risenoveltoonback.entity.ContentsEntity;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;

public interface ToonNovelDataRepository extends JpaRepository<ContentsEntity, Long> {
       Optional<ToonNovelDto> findAllByContentId(Long contentId);
}
