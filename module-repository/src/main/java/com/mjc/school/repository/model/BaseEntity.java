package com.mjc.school.repository.model;

import javax.persistence.*;

public interface BaseEntity<K> {

    K getId();

    void setId(K id);

}
