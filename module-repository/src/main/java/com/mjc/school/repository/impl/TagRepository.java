package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TagModel> readAll() {
        return em.createQuery("select t from TagModel t", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(em.find(TagModel.class, id));
    }

    @Transactional
    @Override
    public TagModel create(TagModel entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public TagModel update(TagModel entity) {
        if (existById(entity.getId())) {
            em.merge(entity);
            return entity;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return em.createQuery("delete from TagModel t where t.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public boolean existById(Long id) {
        return em.find(TagModel.class, id) != null;
    }
}
