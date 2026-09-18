package com.domain.risenoveltoonback.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;
import com.domain.risenoveltoonback.model.toonNovel.ToonNovelEpisodeDto;

@Mapper
public interface ToonNovelDataMapper {

    int existsContent(@Param("userId") String userId, @Param("contentId") Long contentId);
    void updateView(@Param("userId") String userId, @Param("contentId") Long contentId);
    void upCountView(Long contentId);
    Optional<ToonNovelDto> findAllToonNovel(Long contentId);
    void setLoveState(@Param("userId") String userId, @Param("contentId") Long contentId);
    void deleteLoveState(@Param("userId") String userId, @Param("contentId") Long contentId);
    List<ToonNovelEpisodeDto> novelToonEpisodesData(Long contentId);
}
