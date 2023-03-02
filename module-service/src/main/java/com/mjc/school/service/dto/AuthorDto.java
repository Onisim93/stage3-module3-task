package com.mjc.school.service.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class AuthorDto extends BaseDto{
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public AuthorDto(String name) {
        this.name = name;
    }
}
