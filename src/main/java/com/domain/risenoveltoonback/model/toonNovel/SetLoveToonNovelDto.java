package com.domain.risenoveltoonback.model.toonNovel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
public class SetLoveToonNovelDto {
    private String userId;
    private Long contentId;
    private boolean loveOn;
}
