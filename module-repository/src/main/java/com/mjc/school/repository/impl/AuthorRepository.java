package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private EntityManager em;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.em = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<AuthorModel> readAll() {
        return em.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        AuthorModel authorModel = em.find(AuthorModel.class, id);
        return Optional.ofNullable(authorModel);
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        if (!existById(entity.getId())) {
            return null;
        }
        em.getTransaction().begin();
        AuthorModel model = em.getReference(AuthorModel.class, entity.getId());
        model.setName(entity.getName());
        em.getTransaction().commit();
        return model;
    }

    @Override
    public boolean deleteById(Long id) {
        em.getTransaction().begin();
        boolean result = em.createQuery("delete from AuthorModel a where a.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        em.getTransaction().commit();
        return result;
    }

    @Override
    public boolean existById(Long id) {
        return em.find(AuthorModel.class, id) != null;
    }
}
