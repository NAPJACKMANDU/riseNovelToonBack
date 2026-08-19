package com.domain.risenoveltoonback.model.toonNovel;

import java.time.LocalDateTime;

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
public class ToonNovelDto {
private Long contentId;
private String title;
private String description;
private String author;
private String type;
private String toonUrl;
private LocalDateTime createdAt;
private String cpName;
private String rightMember;
private Integer views;
private String leftMember;
}
