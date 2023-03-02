package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Table(name = "author")
@EntityListeners(value = AuditingEntityListener.class)
public class AuthorModel extends BaseEntity{

    private String name;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    public AuthorModel(Long id) {
        super(id);
    }
}
