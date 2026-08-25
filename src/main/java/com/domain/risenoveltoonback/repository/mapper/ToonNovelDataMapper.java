package com.domain.risenoveltoonback.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;

@Mapper
public interface ToonNovelDataMapper {

    int existsContent(@Param("userId") String userId, @Param("contentId") Long contentId);
    void updateView(@Param("userId") String userId, @Param("contentId") Long contentId);
    void upCountView(Long contentId);
    Optional<ToonNovelDto> findAllToonNovel(Long contentId);
    
}
