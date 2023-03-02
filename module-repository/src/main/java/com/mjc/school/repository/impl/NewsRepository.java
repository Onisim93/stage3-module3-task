package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
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
        if (!existById(entity.getId())) {
            return null;
        }
        NewsModel model = em.getReference(NewsModel.class, entity.getId());
        model.setContent(entity.getContent());
        model.setTitle(entity.getTitle());
        model.setAuthor(entity.getAuthor());
        model.setTags(entity.getTags());
        em.flush();
        return model;
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
