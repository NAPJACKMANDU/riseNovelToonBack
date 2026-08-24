package com.domain.risenoveltoonback.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.domain.risenoveltoonback.model.toonNovel.ToonNovelDto;

@Mapper
public interface ToonNovelDataMapper {

    int existsContent(@Param("userId") String userId, @Param("contentId") Long contentId);
    void updateView(ToonNovelDto toonNovelDto);
    
}
