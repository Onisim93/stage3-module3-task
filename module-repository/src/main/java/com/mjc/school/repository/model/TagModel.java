package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "tag")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = AuditingEntityListener.class)
public class TagModel extends BaseEntity{

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<NewsModel> news;
}
