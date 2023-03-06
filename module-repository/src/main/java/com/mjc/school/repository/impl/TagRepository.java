package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {

    private EntityManager em;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.em = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<TagModel> readAll() {
        return em.createQuery("select t from TagModel t", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(em.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        if (existById(entity.getId())) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        boolean result = em.createQuery("delete from TagModel t where t.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        em.getTransaction().commit();
        return result;
    }

    @Override
    public boolean existById(Long id) {
        return em.find(TagModel.class, id) != null;
    }
}
