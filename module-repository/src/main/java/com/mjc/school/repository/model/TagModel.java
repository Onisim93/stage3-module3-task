package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "tag")
@EqualsAndHashCode
@EntityListeners(value = AuditingEntityListener.class)
public class TagModel implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<NewsModel> news;

    public TagModel(Long id) {
        this.id = id;
    }
}
