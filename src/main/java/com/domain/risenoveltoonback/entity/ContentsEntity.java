package com.domain.risenoveltoonback.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "contents")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    private String type;

    @Column(name = "toon_url")
    private String toonUrl;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "cp_name")
    private String cpName;

    @Column(name = "right_member")
    private String rightMember;

    @Column(name = "left_member")
    private String leftMember;

    @Column(name = "views")
    private Integer views;
}
