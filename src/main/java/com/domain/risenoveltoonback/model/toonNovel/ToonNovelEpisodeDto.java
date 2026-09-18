package com.domain.risenoveltoonback.model.toonNovel;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 

public class ToonNovelEpisodeDto {
private int episodeId;
private Long contentId;
private int episodeNumber;
private int subTitle;
private String contentPath;
private int price;
}
