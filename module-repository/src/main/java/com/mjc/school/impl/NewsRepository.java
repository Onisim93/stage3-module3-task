package com.mjc.school.impl;

import com.mjc.school.BaseRepository;
import com.mjc.school.model.NewsModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<NewsModel> readAll() {
        return em.createQuery("select n from NewsModel n", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(em.find(NewsModel.class, id));
    }

    @Transactional
    @Override
    public NewsModel create(NewsModel entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public NewsModel update(NewsModel entity) {
        if (existById(entity.getId())) {
            em.merge(entity);
            return entity;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return em.createQuery("delete from NewsModel m where m.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public boolean existById(Long id) {
        return em.find(NewsModel.class, id) != null;
    }
}
