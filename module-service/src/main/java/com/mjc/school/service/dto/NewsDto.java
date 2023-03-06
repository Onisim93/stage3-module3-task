package com.mjc.school.service.dto;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "tagIds")
public class NewsDto extends BaseDto{
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    private List<TagDto> tagList;
    private List<Long> tagIds;

    public NewsDto(String title, String content) {
        this.title = title;
        this.content = content;
        tagList = new ArrayList<>();
    }

    public NewsDto(String title, String content, Long authorId, List<Long> tagIds) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tagIds = tagIds;
    }

    public NewsDto(Long id, String title, String content, Long authorId, List<Long> tagIds) {
        super(id);
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tagIds = tagIds;
    }
}
