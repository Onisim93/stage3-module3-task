package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private EntityManager em;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.em = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<NewsModel> readAll() {
        return em.createQuery("select n from NewsModel n", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(em.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        if (!existById(entity.getId())) {
            return null;
        }
        em.getTransaction().begin();
        NewsModel model = em.getReference(NewsModel.class, entity.getId());
        model.setContent(entity.getContent());
        model.setTitle(entity.getTitle());
        model.setAuthor(entity.getAuthor());
        model.setTags(entity.getTags());
        em.getTransaction().commit();
        return model;
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().commit();
        boolean result = em.createQuery("delete from NewsModel m where m.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        em.getTransaction().commit();
        return result;
    }

    @Override
    public boolean existById(Long id) {
        return em.find(NewsModel.class, id) != null;
    }
}
