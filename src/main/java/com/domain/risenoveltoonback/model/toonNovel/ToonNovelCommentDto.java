package com.domain.risenoveltoonback.model.toonNovel;

import java.time.LocalDateTime;

import org.attoparser.dom.Text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class ToonNovelCommentDto {
    private Integer commentId;
    private String userId;
    private Integer episodeId;
    private String commentText;
    private LocalDateTime createdAt;
    private String profileImg;
    private Integer contentId;
}
