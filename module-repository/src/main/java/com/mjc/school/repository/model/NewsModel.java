package com.mjc.school.repository.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "news")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = AuditingEntityListener.class)
public class NewsModel extends BaseEntity{

    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AuthorModel author;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<TagModel> tags;
}
