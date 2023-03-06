package com.mjc.school.service.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class TagDto extends BaseDto{
    private String name;
    private List<NewsDto> news;

    public TagDto(String name) {
        this.name = name;
    }

    public TagDto(Long id, String name) {
        super(id);
        this.name = name;
    }
}
