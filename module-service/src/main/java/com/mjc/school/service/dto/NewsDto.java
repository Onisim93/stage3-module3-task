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
@ToString(callSuper = true)
public class NewsDto extends BaseDto{
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    private List<TagDto> tagList;

    public NewsDto(String title, String content) {
        this.title = title;
        this.content = content;
        tagList = new ArrayList<>();
    }
}
