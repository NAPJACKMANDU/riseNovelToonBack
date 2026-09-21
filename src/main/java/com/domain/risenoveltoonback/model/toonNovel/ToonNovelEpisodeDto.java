package com.domain.risenoveltoonback.model.toonNovel;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 

public class ToonNovelEpisodeDto {
    private int episodeId;
    private int contentId;
    private int episodeNumber;
    private String subTitle;
    private String contentPath;
    private int price;
    private LocalDateTime createdAt;
    private boolean isFavorite;
}
