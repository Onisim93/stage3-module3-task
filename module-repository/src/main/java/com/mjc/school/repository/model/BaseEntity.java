package com.mjc.school.repository.model;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
}
